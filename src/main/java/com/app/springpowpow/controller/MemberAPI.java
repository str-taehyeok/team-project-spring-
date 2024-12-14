package com.app.springpowpow.controller;

import com.app.springpowpow.domain.MemberVO;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member/*")
public class MemberAPI {

    private final JwtTokenUtil jwtTokenUtil;
    private final MemberService memberService;
    private final SnsService snsService;
    private final MemberVO memberVO;


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
    public ResponseEntity<Map<String, Object>> login(@RequestBody MemberVO oauthMemberVO) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> claims = new HashMap<>();

        Long memberId = memberService.getMemberIdByEmail(oauthMemberVO.getMemberEmail());
        if (memberId == null) {
            response.put("message", "등록되지 않은 이메일 입니다.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
//        유저를 찾는다.
        MemberVO foundUser = memberService.getMemberById(memberId).orElse(null);

//        비밀번호 검사 방어코드
        if (!foundUser.getMemberPassword().equals(oauthMemberVO.getMemberPassword())) {
            response.put("message", "비밀번호가 틀렸습니다. 확인해주세요.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
//        아이디, 비밀번호가 일치하는 사용자
//        토큰 생성
        claims.put("memberId", foundUser.getId());
        claims.put("email", foundUser.getMemberEmail());
        claims.put("name", foundUser.getMemberName());
        String jwtToken = jwtTokenUtil.generateToken(claims);

//        토큰 응답
        response.put("jwtToken", jwtToken);
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
            schema = @Schema(type="number"), // DB의 스키마를 의미하는 것이 아니라, Swagger에서 인식하기 위한 타입
            in = ParameterIn.PATH, // path로 전달
            required = true
    )
    @GetMapping("member/{id}")
    public MemberVO getMember(@PathVariable Long id) {

        Optional<MemberVO> foundUser = memberService.getMemberById(id);
        if (foundUser.isPresent()) {
            return foundUser.get();
        }

        return new MemberVO();
    }


//  회원탈퇴(구매자)
    @Operation(summary = "회원탈퇴", description = "회원정보 탈퇴할 수 있는 API")
    @Parameter( name = "id", description = "회원 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
    @ApiResponse(responseCode = "200", description = "회원정보 탈퇴 완료")
    @DeleteMapping("/buyer/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.withdraw(id);
    }

//  회원탈퇴(판매자)
    @Operation(summary = "판매자회원탈퇴", description = "회원정보 탈퇴할 수 있는 API")
    @Parameter( name = "id", description = "판매자 회원 번호", schema = @Schema(type="number"), in = ParameterIn.PATH, required = true )
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
        String memberEmail = memberService.findEmailByMemberPhone(memberPhone);
    return memberEmail;
    }
//
//    // SMS 전송
//    @PostMapping("sms/find-id")
//    public ResponseEntity<Map<String, Object>> transferSmsForFindId(@RequestBody String memberPhone) throws IOException {
//        return snsService.transferMessage(memberPhone);
//    }
//
//    // 회원 정보 조회 (휴대폰 번호로 이메일 찾고, 이메일로 회원 조회)
//    @Operation( summary = "회원 조회", description = "휴대폰 번호로 회원 이메일을 찾고, 이메일로 회원 정보를 조회할 수 있는 API")
//    @Parameters({
//            @Parameter(name = "memberPhone", description = "회원의 휴대폰 번호", schema = @Schema(type = "string", example = "010-1234-5678", description = "회원의 전화번호를 입력해주세요."), required = true)
//    })
//    @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공")
//    @ApiResponse(responseCode = "404", description = "회원이 존재하지 않음")
//    @GetMapping("find-id/{memberPhone}")
//    public ResponseEntity<Map<String, Object>> findMemberByIdAndPhone(@PathVariable String memberPhone) throws IOException {
//        Map<String, Object> response = new HashMap<>();
//
//        // 1) 휴대폰 번호를 화면에서 가져온다. (이메일 찾기)
//        Optional<String> foundUser = memberService.getEmailById(memberPhone);
//
//        // 2) 휴대폰 번호로 이메일을 찾을 수 있는 쿼리 생성
//        if (foundUser.isEmpty()) {
//            response.put("message", "이름 또는 휴대폰 번호가 일치하지 않습니다.");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        }
//
//        String memberEmail = foundUser.get();
//
//        // 3) 이메일로 유저를 찾을 수 있는 쿼리를 만든다. (정보 조회)
//        List<MemberVO> members = memberService.findMemberByEmail(memberEmail);
//
//        // 4) 이메일이 있으면 리턴, 없으면 회원이 아닙니다.
//        if (members.isEmpty()) {
//            response.put("message", "회원이 존재하지 않습니다.");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        }
//
//        response.put("message", "회원 정보 조회 성공");
////        response.put("member", members.get(0));
//        return ResponseEntity.ok(response);
//    }

}