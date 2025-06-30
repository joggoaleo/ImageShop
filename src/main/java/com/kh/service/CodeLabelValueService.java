package com.kh.service;

import java.util.List;

import com.kh.common.CodeLabelValue;

public interface CodeLabelValueService {
	
	public List<CodeLabelValue> labelList() throws Exception;
	public List<CodeLabelValue> getCodeList(String groupCode) throws Exception;
}
