package com.kh.service;

import java.util.List;

import com.kh.domain.Member;

public interface MemberService {
	public void register(Member member) throws Exception;
	public void modify(Member member) throws Exception;
	public void remove(int userNo) throws Exception;
	public Member read(Member member) throws Exception;
	public List<Member>list() throws Exception;
	
	// 회원 테이블의 데이터 건수를 반환한다. 
	public int countAll() throws Exception; 
	// 최초 관리자 생성을 위한 데이터를 등록한다. 
	public void setupAdmin(Member member) throws Exception; 
}
