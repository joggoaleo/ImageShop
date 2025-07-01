package com.kh.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.common.CodeLabelValue;
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
		// 회원 등록할 떄, 디폴트 권한값도 같이 할당
		mapper.insert(member);
		MemberAuth memberAuth = new MemberAuth();
		memberAuth.setAuth("ROLE_MEMBER");
		// member_seq.CURRVAL
		mapper.insertAuth(memberAuth);
	}

	@Transactional
	@Override
	public void modify(Member member) throws Exception {
		mapper.update(member);
		// 멤버 권한 삭제
		int userNo = member.getUserNo();
		mapper.deleteAuth(userNo);
		// 수정한 회원 권한 등록
		List<MemberAuth> authList = member.getAuthList();
		for (MemberAuth e : authList) {
			if (e == null) {
				continue;
			}
			if (e.getAuth().trim().length() == 0) {
				continue;
			}
			e.setUserNo(userNo);
			mapper.updateAuth(e);
		}

	}

	@Transactional
	@Override
	public void remove(int userNo) throws Exception {
		 
		// 회원 권한 삭제 
		mapper.deleteAuth(userNo); 
		 
		mapper.delete(userNo); 
	}

	@Override
	public Member read(Member member) throws Exception {
		// 직업코드 목록을 조회하여 뷰에 전달 

		return mapper.select(member);
	}

	@Override
	public List<Member> list() throws Exception {
		return mapper.selectAll();
	}

	// 회원 테이블의 데이터 건수를 반환한다.
	@Override
	public int countAll() throws Exception {
		return mapper.countAll();
	}

	// 최초 관리자를 생성한다.
	@Transactional
	@Override
	public void setupAdmin(Member member) throws Exception {
		mapper.insert(member);
		MemberAuth memberAuth = new MemberAuth();

		memberAuth.setUserNo(member.getUserNo());
		memberAuth.setAuth("ROLE_ADMIN");
		mapper.insertAuth(memberAuth);
	}

}
