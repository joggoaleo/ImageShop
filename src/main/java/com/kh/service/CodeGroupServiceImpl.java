package com.kh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.domain.CodeGroup;
import com.kh.mapper.CodeGroupMapper;
@Service
public class CodeGroupServiceImpl implements CodeGroupService {

	@Autowired
	private CodeGroupMapper mapper;

	@Override
	public void register(CodeGroup codegroup) throws Exception {
		mapper.insert(codegroup);
	}

	@Override
	public void modify(CodeGroup codegroup) throws Exception {
		mapper.update(codegroup);
	}

	@Override
	public void remove(CodeGroup codegroup) throws Exception {
		mapper.delete(codegroup);
	}

	@Override
	public CodeGroup read(CodeGroup codegroup) throws Exception {
		return mapper.select(codegroup);
	}

	@Override
	public List<CodeGroup> list() throws Exception {
		return mapper.selectAll();
	}
}
