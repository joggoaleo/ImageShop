package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.domain.CodeDetail;
import com.kh.mapper.CodeDetailMapper;
@Service
public class CodeDetailServiceImpl implements CodeDetailService {
	
	@Autowired
	private CodeDetailMapper mapper;
	@Override
	public void register(CodeDetail codeDetail) throws Exception {
		//maxSortSeq 따로 등록해주고 할당
		String groupCode = codeDetail.getGroupCode();
		//그룹코드 정렬 순서의 최대값(맨처음에 해당되는 그룹코드로 입력되었을경우는 0을 리턴한다.)
		//해당되는 그룹코드로 몇개의 코드디테일이 등록이 되었는지 체크
		int maxSortSeq =  mapper.getMaxSortSeq(groupCode);
		codeDetail.setSortSeq(maxSortSeq+1);
		mapper.insert(codeDetail);
	}

	@Override
	public void modify(CodeDetail codeDetail) throws Exception {
		mapper.update(codeDetail);

	}

	@Override
	public void remove(CodeDetail codeDetail) throws Exception {
		mapper.delete(codeDetail);

	}

	@Override
	public CodeDetail read(CodeDetail codeDetail) throws Exception {
		
		return mapper.select(codeDetail);
	}

	@Override
	public List<CodeDetail> list() throws Exception {
		return mapper.selectAll();
	}

	

}
