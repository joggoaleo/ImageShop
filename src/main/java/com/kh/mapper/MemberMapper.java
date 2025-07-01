package com.kh.mapper;

import java.util.List;

import com.kh.domain.Member;
import com.kh.domain.MemberAuth;

public interface MemberMapper {
	public void insert(Member member) throws Exception;
	public void insertAuth(MemberAuth memberAuth) throws Exception;
	public void update(Member member) throws Exception;
	public void updateAuth(MemberAuth memberAuth) throws Exception;
	public void delete(int userNo) throws Exception;
	public void deleteAuth(int userNo) throws Exception;
	public Member select(Member member) throws Exception;
	public List<Member>selectAll() throws Exception;
	// 회원 테이블의 데이터 건수 조회 
	public int countAll() throws Exception;
	public Member readByUserId(String userId);
}
