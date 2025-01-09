package com.app.springpowpow.controller;

import com.app.springpowpow.domain.MemberVO;
import com.app.springpowpow.domain.PetVO;
import com.app.springpowpow.service.MemberService;
import com.app.springpowpow.service.SnsService;
import com.app.springpowpow.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member/*")
public class MemberAPI {

    private final JwtTokenUtil jwtTokenUtil;
    private final MemberService memberService;
    private final SnsService snsService;
    private final HttpSession session;


    //    회원가입
    @Operation(summary = "회원가입", description = "회원가입을 할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "회원가입 완료")
    @PostMapping("register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody MemberVO memberVO) {
        Map<String, Object> claims = new HashMap<>();
        Map<String, Object> response = new HashMap<>();

        claims.put("email", memberVO.getMemberEmail());
        claims.put("name", memberVO.getMemberName());

        // JWT 토큰 생성
        String jwtToken = jwtTokenUtil.generateToken(claims);

        // 방어 코드, early return
        Long memberId = memberService.getMemberIdByEmail(memberVO.getMemberEmail());
        if (memberId != null) {
            MemberVO foundUser = memberService.getMemberById(memberId).orElse(null);

            if (foundUser != null && foundUser.getMemberEmail().equals(memberVO.getMemberEmail())) {
                response.put("message", "이미 사용중인 아이디입니다.");
                response.put("provider", foundUser.getMemberProvider());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
        }

        // 아니라면 소설 로그인 사용자인지 검사한다
        if (memberVO.getMemberProvider() == null) {
            memberVO.setMemberProvider("구매자");
        }

        memberService.register(memberVO);
        response.put("jwtToken", jwtToken);

        return ResponseEntity.ok(response);
    }

    ;

    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestParam String type,
            @RequestBody MemberVO oauthMemberVO
    ) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> claims = new HashMap<>();

        Long memberId = memberService.getMemberIdByEmail(oauthMemberVO.getMemberEmail());
        if (memberId == null) {
            response.put("message", "등록되지 않은 이메일 입니다.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
//        유저를 찾는다.
        MemberVO foundUser = null;

//        구매자 로그인인지, 판매자 로그인인지 확인한다.
//        확인 후 로그인 처리
        if(type.equals("buyer")){
            // 구매자 로그인 처리 확인
            foundUser = memberService.getMemberByIdAndType(memberId, "구매자").orElse(null);
        }else if(type.equals("seller")){
            // 판매자 로그인 처리 확인
            foundUser = memberService.getMemberByIdAndType(memberId, "판매자").orElse(null);
        }else if(type.equals("admin")) {
            foundUser = memberService.getMemberByIdAndType(memberId, "관리자").orElse(null);
        }

//        회원은 있지만 다른 타입으로 로그인 한 경우
        if (foundUser == null) {
            String provider = memberService.getMemberById(memberId).get().getMemberProvider();
            response.put("message", provider + "회원 입니다.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

//        비밀번호 검사 방어코드
        if (!foundUser.getMemberPassword().equals(oauthMemberVO.getMemberPassword())) {
            response.put("message", "비밀번호가 틀렸습니다. 확인해주세요.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

//        아이디, 비밀번호가 일치하는 사용자
        String memberProvider = foundUser.getMemberProvider();

//        토큰 생성
        claims.put("email", foundUser.getMemberEmail());
        claims.put("name", foundUser.getMemberName());
        claims.put("provider", memberProvider);
        String jwtToken = jwtTokenUtil.generateToken(claims);

//        토큰 응답
        response.put("jwtToken", jwtToken);
        response.put("provider", memberProvider);
        return ResponseEntity.ok(response);
    }



    //    토큰 정보로 유저를 가져온다.
    @GetMapping("token")
    public ResponseEntity<Map<String, Object>> token(
            @RequestHeader(value = "Authorization", required = false) String jwtToken
    ) {
        Map<String, Object> response = new HashMap<>();
        String token = jwtToken != null ? jwtToken.replace("Bearer ", "") : jwtToken;

        // 토큰이 존재하고, 유효하고, 토큰의 정보가 일치하면
        if (token != null && jwtTokenUtil.isTokenValid(token)) {
            // 토큰을 유저정보로 바꾼다
            Claims claims = jwtTokenUtil.parseToken(token);
            String memberEmail = (String) claims.get("email");
            Long memberId = memberService.getMemberIdByEmail(memberEmail);
            MemberVO foundUser = memberService.getMemberById(memberId).orElse(null);

            response.put("currentUser", foundUser);

            return ResponseEntity.ok(response);
        }

        response.put("message", "토큰이 만료되었습니다.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    //  Sms 인증
    @PostMapping("sms")
    public ResponseEntity<Map<String, Object>> transferSms(@RequestBody String memberPhone) throws IOException {
        return snsService.transferMessage(memberPhone);
    }

    // 이메일 중복검사
    @PostMapping("/check-email")
    public ResponseEntity<Map<String, Object>> checkEmail(@RequestBody Map<String, String> req) {
        String memberEmail = req.get("memberEmail");

        // 이메일 형식 유효성 검증
        boolean isValid = memberEmail != null && memberEmail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

        Map<String, Object> response = new HashMap<>();
        if (isValid) {
            // DB에서 이메일 존재 여부 확인
            boolean exists = memberService.checkDuplicate(memberEmail);

            if (exists) {
                response.put("isValid", false);
                response.put("message", "이미 사용 중인 이메일입니다.");
            } else {
                response.put("isValid", true);
                response.put("memberEmail", memberEmail);
            }
        } else {
            response.put("isValid", false);
            response.put("message", "유효하지 않은 이메일입니다.");
        }

        return ResponseEntity.ok(response);
    }

    //  단일회원조회
    @Operation(summary = "회원정보 조회", description = "회원 개인정보를 조회할 수 있는 API")
    @Parameter(
            name = "id",
            description = "회원 번호",
            schema = @Schema(type = "number"), // DB의 스키마를 의미하는 것이 아니라, Swagger에서 인식하기 위한 타입
            in = ParameterIn.PATH, // path로 전달
            required = true
    )
    @GetMapping("/member/{memberId}")
    public MemberVO getMember(@PathVariable Long memberId) {

        Optional<MemberVO> foundUser = memberService.getMemberById(memberId);
        if (foundUser.isPresent()) {
            return foundUser.get();
        }

        return new MemberVO();
    }


    //  회원탈퇴(구매자)
    @Operation(summary = "회원탈퇴", description = "회원정보 탈퇴할 수 있는 API")
    @Parameter(name = "id", description = "회원 번호", schema = @Schema(type = "number"), in = ParameterIn.PATH, required = true)
    @ApiResponse(responseCode = "200", description = "회원정보 탈퇴 완료")
    @DeleteMapping("/buyer/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.withdraw(id);
    }

    //  회원탈퇴(판매자)
    @Operation(summary = "판매자회원탈퇴", description = "회원정보 탈퇴할 수 있는 API")
    @Parameter(name = "id", description = "판매자 회원 번호", schema = @Schema(type = "number"), in = ParameterIn.PATH, required = true)
    @ApiResponse(responseCode = "200", description = "회원정보 탈퇴 완료")
    @DeleteMapping("/seller/{id}")
    public void deleteSeller(@PathVariable Long id) {
        memberService.withdrawSeller(id);
    }


////////////////////////////////////////////////////////////////////////////////////////// 아이디 찾기

    @Operation(summary = "이메일(아이디) 찾기", description = "이메일(아이디)를 찾을 수 있는 API")
    @ApiResponse(responseCode = "200", description = "아이디 찾기 성공")
    @PostMapping("/find-id")
    public String findId(@RequestBody String memberPhone) {

        // 이름과 전화번호로 회원을 조회
        String memberEmail = memberService.findEmail(memberPhone);
        return memberEmail;
    }

    // SMS 전송
//    @PostMapping("/sms/find-id")
//    public ResponseEntity<Map<String, Object>> transferSmsForFindId(@RequestBody String memberPhone) throws IOException {
//        return snsService.transferMessage(memberPhone);
//    }


    // 회원 조회 API
    @Operation(summary = "회원 조회", description = "휴대폰 번호로 회원 이메일을 찾고, 이메일로 회원 정보를 조회할 수 있는 API")
    @Parameters({
            @Parameter(name = "memberPhone", description = "회원의 휴대폰 번호", schema = @Schema(type = "string", example = "01012345678", description = "회원의 전화번호를 입력해주세요."), required = true)
    })
    @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공")
    @ApiResponse(responseCode = "404", description = "회원이 존재하지 않음")
    @GetMapping("/find-id/{memberPhone}")
    public ResponseEntity<MemberVO> findMemberByIdAndPhone(@PathVariable String memberPhone) {
        // 2) 번호로 이메일을 찾는다.
        String memberEmail = memberService.findEmail(memberPhone);

        if (memberEmail != null) {
            // 3) 이메일로 회원 정보를 찾는다.
            Optional<MemberVO> members = memberService.findMember(memberEmail);

            if (members.isPresent()) {
                return ResponseEntity.ok(members.get());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    ////////////////////////////////////////////////////////////////////////////////////////// 아이디 찾기 끝


//    어드민에서 회원 찾기
    @Operation(summary = "어드민에서 일반회원과 판매자 전체조회", description = "구매자 또는 판매자 유형에 따라 회원 정보를 조회할 수 있는 API")
    @Parameters({
            @Parameter(
                    name = "memberProvider",
                    description = "구매자 또는 판매자",
                    schema = @Schema(type = "string", example = "구매자", description = "조회하고 싶은 회원의 타입을 입력해주세요"))
    })
    @GetMapping("/admin/members")
    public ResponseEntity<List<MemberVO>> getMemberListByType(
            @RequestParam(name = "memberProvider") String memberProvider) {

        List<MemberVO> result;

        if ("구매자".equals(memberProvider)) {
            result = memberService.findBuyers();
        } else if ("판매자".equals(memberProvider)) {
            result = memberService.findSellers();
        } else {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        if (!result.isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "유저 정보 변경", description = "유저 정보를 업데이트 하는 API")
    @Parameter( name = "id", description = "멤버 번호", schema = @Schema(type = "number"), in = ParameterIn.PATH, required = true )
    @ApiResponse(responseCode = "200", description = "정보 변경 완료")
    @PutMapping("update/{id}")
    public MemberVO modify(@PathVariable Long id, @RequestBody MemberVO memberVO) {
        memberVO.setId(id);
        memberService.modify(memberVO);
        return memberVO;
    }


    // 비밀번호 로직 >>> 이메일로 인증번호 전송 후, 인증번호 저장
    @PostMapping("/find-password")
    public ResponseEntity<Map<String, Object>> findPassword(@RequestBody Map<String, String> req) {
        Map<String, Object> response = new HashMap<>();
        String memberEmail = req.get("memberEmail");

        // 이메일 입력 확인
        if (memberEmail == null || memberEmail.isEmpty()) {
            response.put("message", "이메일을 입력해주세요.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // 6자리 인증 코드 생성
        String verificationCode = String.format("%06d", (int)(Math.random() * 900000) + 100000);

        // 인증번호 세션에 저장
        session.setAttribute("verificationCode", verificationCode);

        // 이메일로 인증번호 전송
        return snsService.sendEmailVerification(memberEmail);
    }

    // 비밀번호 변경 (change-password)
    @PostMapping("/change-password")
    public ResponseEntity<Map<String, Object>> changePassword(@RequestBody Map<String, String> req) {
        Map<String, Object> response = new HashMap<>();
        String memberEmail = req.get("memberEmail");
        String newPassword = req.get("newPassword");

        // 비밀번호 변경 로직
        memberService.updatePassword(memberEmail, newPassword);
        response.put("message", "비밀번호가 성공적으로 변경되었습니다.");
        return ResponseEntity.ok(response);
    }


//    전체 회원정보조회
    @Operation(summary = "회원 전체 리스트", description = "회원 정보 전체 볼 수 있는 API")
    @Parameters({
            @Parameter(name = "id", description = "회원 번호", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberEmail", description = "회원 이메일", schema = @Schema(type = "string", format = "date"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberName", description = "회원 이름", schema = @Schema(type = "string", format = "date"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberPhone", description = "회원 전화번호", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberNickname", description = "회원 닉네임", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberZipcode", description = "우편번호", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberAddress", description = "회원 주소", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberAddressDetail", description = "회원 상세주소", schema = @Schema(type = "string", format = "date"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberFileName", description = "회원 이미지파일 이름", schema = @Schema(type = "string", format = "date"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberFilePath", description = "회원 이미지파일 경로", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberSmsCheck", description = "회원 문자수신동의", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberEmailCheck", description = "회원 이메일수신동의", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberDate", description = "회원 가입날짜", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberBusinessNumber", description = "사업자번호", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberProvider", description = "회원 프로바이더", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberBusinessName", description = "판매자 업체명", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberBank", description = "은행명", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberBankAccount", description = "계좌번호", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true)
    })
    @GetMapping("admin/member-list")
    public List<MemberVO> getList() {
        return memberService.getAllMembers();
    }


    @Operation(summary = "비밀번호 체크", description = "redux의 이메일과 일치하는 비밀번호가 입력 비밀번호랑 일치하는지 확인하는 API")
    @PostMapping("/check-password")
    public ResponseEntity<Map<String, Object>> checkPassword(@RequestBody MemberVO memberVO) {

//         이메일과 비밀번호를 확인하는 로직
        boolean isPasswordMatch = memberService.checkPassword(memberVO);

        Map<String, Object> response = new HashMap<>();
        response.put("success", isPasswordMatch);
        response.put("message", isPasswordMatch ? "비밀번호가 일치합니다." : "비밀번호가 일치하지 않습니다.");

        return ResponseEntity.ok(response); // 200 OK
    }

//    -- 이미지 업로드--

    @Operation(summary = "이미지 업로드", description = "이미지를 저장하는 API")
    @ApiResponse(responseCode = "200", description = "이미지 업로드 완료")
    @PostMapping("upload")
    public ResponseEntity<Map<String, String>> upload(
            @RequestParam("memberName") String memberName,
            @RequestParam("memberNickname") String memberNickname,
            @RequestParam("memberEmail") String memberEmail,
            @RequestParam("memberPhone") String memberPhone,
            @RequestParam("memberZipcode") String memberZipcode,
            @RequestParam("memberAddress") String memberAddress,
            @RequestParam("memberAddressDetail") String memberAddressDetail,
            @RequestParam("uploadFile") MultipartFile uploadFile
    ) throws IOException {

        Map<String, String> response = new HashMap<>();
        String rootPath = "C:/upload/" + getPath();

        if(!uploadFile.getOriginalFilename().equals("blob")){
            File file = new File(rootPath);
            if (!file.exists()) {
                file.mkdirs();
            }

            //        uuid 생성
            String uuid = UUID.randomUUID().toString();
            log.info("Generated UUID: {}", uuid);
            uploadFile.transferTo(new File(rootPath, uuid + "_" + uploadFile.getOriginalFilename()));

    //        썸네일
            if(uploadFile.getContentType().startsWith("image")){
                FileOutputStream out = new FileOutputStream(new File(rootPath, "t_" + uuid + "_" + uploadFile.getOriginalFilename()));
                Thumbnailator.createThumbnail(uploadFile.getInputStream(), out, 100, 100);
                out.close();
            }

            log.info("upload path : {}", uuid.toString());

            response.put("message", "이미지와 함께 업로드 완료");
            response.put("uuid", uuid);
            return ResponseEntity.ok(response);
        }

        response.put("message", "이미지 없이 업로드 완료");
        return ResponseEntity.ok(response);
    }

    // 유저수정 API
    @Operation(summary = "유저 수정", description = "유저 수정 API")
    @ApiResponse(responseCode = "200", description = "유저 수정 완료")
    @PutMapping("profileEdit")
    public ResponseEntity<Map<String, String>> profileEdit(
            @RequestParam("memberName") String memberName,
            @RequestParam("memberNickname") String memberNickname,
            @RequestParam("memberEmail") String memberEmail,
            @RequestParam("memberPhone") String memberPhone,
            @RequestParam("memberZipcode") String memberZipcode,
            @RequestParam("memberAddress") String memberAddress,
            @RequestParam("memberAddressDetail") String memberAddressDetail,
            @RequestParam("uuid") String uuid,
            @RequestParam("uploadFile") MultipartFile uploadFile
    ) {
        Map<String, String> response = new HashMap<>();
//        유저를 찾아온다.
        memberService.findMember(memberEmail).ifPresent(memberVO -> {
            MemberVO foundUser = memberVO;
            log.info("found user: {}", foundUser);
            foundUser.setMemberName(memberName);
            foundUser.setMemberNickname(memberNickname);
            foundUser.setMemberEmail(memberEmail);
            foundUser.setMemberPhone(memberPhone);
            foundUser.setMemberZipcode(memberZipcode);
            foundUser.setMemberAddress(memberAddress);
            foundUser.setMemberAddressDetail(memberAddressDetail);
            if(!uuid.equals("")){
                foundUser.setMemberFilePath(getPath());
                foundUser.setMemberFileName(uuid + "_" + uploadFile.getOriginalFilename());
            }
            memberService.modify(foundUser);
        });

        response.put("message", "유저 정보 수정 완료");
        return ResponseEntity.ok(response);
    }

    @GetMapping("display")
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File("C:/upload/" + fileName));
    }

    //    현재 시간을 기준으로 년월일로 관리할 수 있게 경로를 붙인다.
    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }




}