package com.kh.mapper;

import java.util.List;

import com.kh.common.CodeLabelValue;

public interface CodeLabelValueMapper {
	public List<CodeLabelValue> selectAll() throws Exception;
	
	public List<CodeLabelValue> getCodeList(String groupCode) throws Exception;
}
