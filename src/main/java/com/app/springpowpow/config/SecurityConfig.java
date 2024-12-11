package com.app.springpowpow.config;

import com.app.springpowpow.service.MemberService;
import com.app.springpowpow.util.JwtTokenUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final JwtTokenUtil jwtTokenUtil;
    private final MemberService memberService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정 추가
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // 모든 경로 허용
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/google")
                        .successHandler((request, response, authentication) -> {
                            if (authentication instanceof OAuth2AuthenticationToken) {
                                OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
                                OAuth2User user = authToken.getPrincipal();
                                Map<String, Object> attributes = user.getAttributes();

                                // OAuth2 제공자 정보
                                String provider = authToken.getAuthorizedClientRegistrationId();  // ex. "google", "kakao", "naver"
                                String email = (String) attributes.get("email");
                                String name = (String) attributes.get("name");
                                Map<String, Object> responseMap = new HashMap<>();
                                Map<String, Object> claims = new HashMap<>();
                                claims.put("email", email);
                                claims.put("name", name);

                                // 회원을 확인한다. 이미 회원인지, 최초 소셜 로그인을 한 사람인지
                                Long memberId = memberService.getMemberIdByEmail(email);
                                if(memberId != null){
                                    // 이미 회원이라면 JWT 토큰을 발급
                                    String jwtToken = jwtTokenUtil.generateToken(claims);
                                    responseMap.put("jwtToken", jwtToken);
                                    String redirectUrl = "http://localhost:3000/?jwtToken=" + jwtToken;
                                    response.sendRedirect(redirectUrl);
                                }else  {
                                    // 신규 회원이라면 리다이렉트 성공페이지 -> 마이페이지 -> 회원가입 -> 백엔드로 회원가입 처리 요청 보낸다.
                                    String redirectUrl = "http://localhost:3000/join?email=" + email + "&provider=" + provider;
                                    response.sendRedirect(redirectUrl);
                                }

                            }
                        })
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("http://localhost:3000/sign-in")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            HttpSession session = request.getSession(false);  // 세션이 있으면 가져오기
                            if (session != null) {
                                session.invalidate(); // 세션 비우기
                            }
                            response.sendRedirect("http://localhost:3000/login");
                        })
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // React 앱 주소
        configuration.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
        configuration.addAllowedHeader("*"); // 모든 요청 헤더 허용
        configuration.setAllowCredentials(true); // 인증 정보 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 적용
        return source;
    }
}
