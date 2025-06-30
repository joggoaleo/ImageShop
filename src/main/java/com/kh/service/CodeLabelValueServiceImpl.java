package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.common.CodeLabelValue;
import com.kh.mapper.CodeLabelValueMapper;
@Service
public class CodeLabelValueServiceImpl implements CodeLabelValueService {
	@Autowired
	CodeLabelValueMapper mapper;
	
	@Override
	public List<CodeLabelValue> labelList() throws Exception {
		return mapper.selectAll();
	}

	@Override
	public List<CodeLabelValue> getCodeList(String groupCode) throws Exception {
		
		return mapper.getCodeList(groupCode);
	}
	

}
