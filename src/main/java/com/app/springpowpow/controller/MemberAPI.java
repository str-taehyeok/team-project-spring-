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
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member/*")
public class MemberAPI {

    private final JwtTokenUtil jwtTokenUtil;
    private final MemberService memberService;
    private final SnsService snsService;


    //    회원가입
    @Operation(summary = "회원가입", description = "회원가입을 할 수 있는 API")
    @Parameters({
            @Parameter(name = "id", description = "회원 번호", schema = @Schema(type = "number"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberEmail", description = "회원 이메일", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberPassword", description = "회원 비밀번호", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberName", description = "회원 이름", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberPhone", description = "회원 전화번호", schema = @Schema(type = "string"), in = ParameterIn.HEADER, required = true),
            @Parameter(name = "memberNickname", description = "회원 닉네임", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "memberZipcode", description = "회원 우편번호", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "memberAddress", description = "회원 주소", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "memberAddressDetail", description = "회원 상세주소", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "memberImage", description = "회원 프로필이미지", schema = @Schema(type = "string"), in = ParameterIn.HEADER),
            @Parameter(name = "memberSmsCheck", description = "회원 문자수신동의", schema = @Schema(type = "char"), in = ParameterIn.HEADER),
            @Parameter(name = "memberEmailCheck", description = "회원 이메일수신동의", schema = @Schema(type = "char"), in = ParameterIn.HEADER),
            @Parameter(name = "memberBusinessNumber", description = "사업자번호", schema = @Schema(type = "string"), in = ParameterIn.HEADER)
    })
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
            memberVO.setMemberProvider("자사로그인");
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


}
