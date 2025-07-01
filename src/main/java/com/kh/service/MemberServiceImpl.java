package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.config.SecurityConfig;
import com.kh.domain.Member;
import com.kh.domain.MemberAuth;
import com.kh.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper mapper;

	@Transactional
	@Override
	public void register(Member member) throws Exception {
		//회원 등록할 떄, 디폴트 권한값도 같이 할당
		mapper.insert(member);
		MemberAuth memberAuth = new MemberAuth();
		memberAuth.setAuth("ROLE_MEMBER");
		//member_seq.CURRVAL
		mapper.insertAuth(memberAuth);
	}
	@Transactional
	@Override
	public void modify(Member member) throws Exception {
		mapper.update(member);
		//멤버 권한 삭제 
		MemberAuth memberAuth = member.getAuthList().get(0);
		mapper.deleteAuth(memberAuth);
		
		List<MemberAuth> authList = member.getAuthList();
		for(MemberAuth e: authList) {
			if(e==null) {
				continue;
			}
			if(e.equals("")) {
				continue;
			}
			e.setUserNo(member.getUserNo());
			mapper.insertAuth(e);
		}
		
	}

	@Override
	public void remove(Member member) throws Exception {
		mapper.delete(member);
	}

	@Override
	public Member read(Member member) throws Exception {
		return mapper.select(member);
	}

	@Override
	public List<Member> list() throws Exception {
		return mapper.selectAll();
	}

}
