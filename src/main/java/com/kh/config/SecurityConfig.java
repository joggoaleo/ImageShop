package com.kh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity 
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
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
	
	@Bean
	public PasswordEncoder createPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
