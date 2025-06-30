package com.kh.mapper;

import java.util.List;

import com.kh.domain.Member;
import com.kh.domain.MemberAuth;

public interface MemberMapper {
	public void insert(Member member) throws Exception;
	public void insertAuth(MemberAuth memberAuth) throws Exception;
	public void update(Member member) throws Exception;
	public void delete(Member member) throws Exception;
	public void deleteAuth(MemberAuth memberAuth) throws Exception;
	public Member select(Member member) throws Exception;
	public List<Member>selectAll() throws Exception;
}
