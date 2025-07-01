package com.kh.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Configuration
@EnableWebSecurity 
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {
	@Autowired
	DataSource dataSource;
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		log.info("Security Filter");
		//csrf 토큰 비활성화 
		  http.csrf().disable(); 
		//인가설정
		  http.authorizeHttpRequests().
		  requestMatchers("/board/**").authenticated().
		  requestMatchers("/manager/**").hasRole("MANAGER").
		  requestMatchers("/admin/**").hasRole("ADMIN").
		  anyRequest().permitAll();
		  
		  http.formLogin();
		  
		
		return http.build();
	}
	//암호화 처리기 빈 등록. PasswordEncoder autowired하면 호출도미
	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
