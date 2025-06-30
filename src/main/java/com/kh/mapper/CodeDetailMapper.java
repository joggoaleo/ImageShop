package com.kh.mapper;

import java.util.List;

import com.kh.domain.CodeDetail;

public interface CodeDetailMapper {

	public void insert(CodeDetail codeDetail) throws Exception;
	public void update(CodeDetail codeDetail) throws Exception;
	public void delete(CodeDetail codeDetail) throws Exception;
	public CodeDetail select(CodeDetail codeDetail) throws Exception;
	public List<CodeDetail> selectAll() throws Exception;
	// 그룹코드 정렬 순서의 최대값 
	public Integer getMaxSortSeq(String groupCode) throws Exception;
}
