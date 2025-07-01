package com.kh.common.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kh.common.security.domain.CustomUser;
import com.kh.domain.Member;
import com.kh.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired 
	private MemberMapper mapper;
	// 사용자 정의 유저 상세 클래스 메서드-loadUserByUsername의 "username"은 userId이다. 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Load User By UserId : " + username); 
		Member member = mapper.readByUserId(username); 
		log.info("queried by member mapper: " + member); 
		return member == null ? null : new CustomUser(member); 
	}

}
