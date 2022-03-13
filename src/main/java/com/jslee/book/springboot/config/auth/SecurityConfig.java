package com.jslee.book.springboot.config.auth;

import com.jslee.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화시켜줌
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 disable
                .and()
                    .authorizeRequests() // URL별 권한 관리 설정의 시작점. authorizeRequests가 선언되어야 antMatchers 옵션을 사용할 수 있음
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll() // 권한 관리 대상을 지정하는 옵션
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // URL, HTTP 메소드 별로 관리 가능
                    .anyRequest().authenticated() // 설정된 값들 이외의 URL. 여기서는 authenticated()를 추가하여 나머지 URL 모두 인증된 사용자에게만 허용
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                .and()
                    .oauth2Login() // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당
                            .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
    }
}
