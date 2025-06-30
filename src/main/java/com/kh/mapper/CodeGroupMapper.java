package com.kh.mapper;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.domain.CodeGroup;

@Controller 
@RequestMapping("/codegroup") 
public interface CodeGroupMapper {
	
	public void insert(CodeGroup codegroup) throws Exception;
	public void update(CodeGroup codegroup) throws Exception;
	public void delete(CodeGroup codegroup) throws Exception;
	public CodeGroup select(CodeGroup codegroup) throws Exception;
	public List<CodeGroup> selectAll() throws Exception;
	
}
