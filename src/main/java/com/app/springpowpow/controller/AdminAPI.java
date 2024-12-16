package com.app.springpowpow.controller;

import com.app.springpowpow.domain.MemberVO;
import com.app.springpowpow.service.MemberService;
import com.app.springpowpow.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/*")
public class AdminAPI {
    private final JwtTokenUtil jwtTokenUtil;
    private final MemberService memberService;

    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody MemberVO oauthMemberVO) {
        Map<String, Object> claims = new HashMap<>();
        Map<String, Object> response = new HashMap<>();

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


        if (!"관리자".equals(foundUser.getMemberProvider())) {
            response.put("message", "관리자 권한이 없습니다.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }

//        아이디, 비밀번호가 일치하는 사용자
//        토큰 생성
        claims.put("memberId", foundUser.getId());
        claims.put("email", foundUser.getMemberEmail());
        claims.put("name", foundUser.getMemberName());
        String adminToken = jwtTokenUtil.generateToken(claims);

        //        토큰 응답
        response.put("adminToken", adminToken);
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
}
