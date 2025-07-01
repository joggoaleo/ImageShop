package com.kh.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.kh.common.security.CustomAccessDeniedHandler;
import com.kh.common.security.CustomLoginSuccessHandler;
import com.kh.common.security.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
	@Autowired
	DataSource dataSource;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("Security Filter");
		// csrf 토큰 비활성화
		http.csrf().disable();
		// 인가설정
		http.authorizeHttpRequests().requestMatchers("/board/**").authenticated().requestMatchers("/manager/**")
				.hasRole("MANAGER").requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().permitAll();

		http.formLogin();
		// CustomLoginSuccessHandler를 로그인 성공 처리자로 지정한다.
		http.formLogin().loginPage("/auth/login").loginProcessingUrl("/login")
				.successHandler(createAuthenticationSuccessHandler());
		
		// 로그아웃을 하면 자동 로그인에 사용하는 쿠키도 삭제한다 
		  http.logout() 
		  .logoutUrl("/auth/logout") 
		  .invalidateHttpSession(true) 
		  .deleteCookies("remember-me","JSESSION_ID");

		// CustomLoginSuccessHandler를 접근 거부자로 지정한다.
		http.exceptionHandling().accessDeniedHandler(createAccessDeniedHandler());

		// 데이터 소스를 지정하고 테이블을 이용해서 기존 로그인 정보를 기록
		// 쿠키의 유효시간(24시간)을 지정한다.
		http.rememberMe().key("zeus").tokenRepository(createJDBCRepository()).tokenValiditySeconds(60 * 60 * 24);

		return http.build();
	}
	//데이터베이스를 통한 회원인증 관리	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
								//아이디 패스워드 인가정책 디비에 가져와서 Security(User)에게 넘겨주기 CustomUser Super
		auth.userDetailsService(createUserDetailsService()).passwordEncoder(createPasswordEncoder());
	}

	private PersistentTokenRepository createJDBCRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

	// 데이터베이스를 통한 회원인증 관리 관련해서 CustomUserDetailsService를 스프링 빈으로 정의한다.
	@Bean
	public UserDetailsService createUserDetailsService() {
		return new CustomUserDetailsService();
	}
	//실패했을때 로그 뽑아보기
	private AccessDeniedHandler createAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	//성공했을때 로그 뽑아보기
	private AuthenticationSuccessHandler createAuthenticationSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}

	// 암호화 처리기 빈 등록. PasswordEncoder autowired하면 호출도미
	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
