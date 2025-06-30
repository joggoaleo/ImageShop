package com.kh.service;

import java.util.List;

import com.kh.domain.CodeGroup;

public interface CodeGroupService {
	
	public void register(CodeGroup codegroup) throws Exception;
	public void modify(CodeGroup codegroup) throws Exception;
	public void remove(CodeGroup codegroup) throws Exception;
	public CodeGroup read(CodeGroup codegroup) throws Exception;
	public List<CodeGroup> list() throws Exception;
	
}
