package com.kh.service;

import java.util.List;

import com.kh.domain.Member;

public interface MemberService {
	public void register(Member member) throws Exception;
	public void modify(Member member) throws Exception;
	public void remove(Member member) throws Exception;
	public Member read(Member member) throws Exception;
	public List<Member>list() throws Exception;
}
