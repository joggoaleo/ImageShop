package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.common.CodeLabelValue;
import com.kh.domain.CodeDetail;
import com.kh.service.CodeDetailService;
import com.kh.service.CodeLabelValueService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("codedetail")
public class CodeDetailController {
@Autowired
private CodeDetailService service;

@Autowired
private CodeLabelValueService labelService;

@GetMapping("registerForm")
public void registerForm(Model model) throws Exception {
	log.info("get registerForm (get over labelList)");
	
	model.addAttribute("groupCodeList",labelService.labelList());
	CodeDetail codeDetail = new CodeDetail();
	model.addAttribute("codeDetail",codeDetail);
}
@PostMapping("register")
public String register(CodeDetail codeDetail,RedirectAttributes rttr) throws Exception {
	//선택된 코드그룹코드 최대갯수+1 -> 코드 디테일 등록
	log.info("post register(groupCode,codeValue,codeName,sort_seq)");
	service.register(codeDetail);
	rttr.addFlashAttribute("msg", "SUCCESS");
	return "redirect:/codedetail/list";
}
@GetMapping("list")
public void list(CodeDetail codeDetail, Model model) throws Exception {
	log.info("get list (get over list)");
	model.addAttribute("list",service.list());
}
@GetMapping("read")
public void read(CodeDetail codeDetail, Model model) throws Exception {
	model.addAttribute("groupCodeList",labelService.labelList());
	model.addAttribute("codeDetail",service.read(codeDetail));
	
}

@GetMapping("modify")
public void modifyForm(CodeDetail codeDetail, Model model) throws Exception {
	model.addAttribute(service.read(codeDetail)); 
	// 그룹코드 목록을 조회하여 뷰에 전달 
	List<CodeLabelValue> groupCodeList = 
			labelService.labelList(); 
	model.addAttribute("groupCodeList", groupCodeList); 
}
@PostMapping("modify")
public String modify(CodeDetail codeDetail, RedirectAttributes rttr) throws Exception{
	service.modify(codeDetail);
	rttr.addFlashAttribute("msg", "SUCCESS");
	return "redirect:/codedetail/list";
}
@PostMapping("remove")
public String remove(CodeDetail codeDetail, RedirectAttributes rttr) throws Exception{
	service.remove(codeDetail);
	rttr.addFlashAttribute("msg", "SUCCESS");
	return "redirect:/codedetail/list";
}
}
