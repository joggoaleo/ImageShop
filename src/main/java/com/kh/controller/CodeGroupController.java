package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.domain.CodeGroup;
import com.kh.service.CodeGroupService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("codeGroup")
public class CodeGroupController {
	@Autowired
	CodeGroupService service;
	
	@GetMapping("register")
	public String registerForm(CodeGroup codeGroup, Model model) throws Exception {
		log.info("Get Codegroup registerForm");
		model.addAttribute(codeGroup);
		return "codeGroup/registerForm";
	}
	
	@PostMapping("register")
	public String register(CodeGroup codeGroup, RedirectAttributes rttr) throws Exception {
		log.info("Post Codegroup registerForm");
		service.register(codeGroup);
		rttr.addFlashAttribute("msg", "등록완료");
		return "redirect:/codeGroup/list";
	}
	@GetMapping("list")
	public void list(Model model) throws Exception {
		log.info("Get Codegroup list");
		
		model.addAttribute("list", service.list());
	}
	@GetMapping("read")
	public void read(CodeGroup codeGroup, Model model) throws Exception {
		log.info("get CodeGroup read");
		
		model.addAttribute("codeGroup", service.read(codeGroup));
	}
	@GetMapping("modify")
	public void modifyForm(CodeGroup codeGroup, Model model) throws Exception {
		log.info("get CodeGroup modify");
		model.addAttribute("codeGroup", service.read(codeGroup));
	}
	//prg
	@PostMapping("modify")
	public String modify(CodeGroup codeGroup, RedirectAttributes rttr) throws Exception{
		log.info("post CodeGroup modify");
		
		
		service.modify(codeGroup);
		rttr.addFlashAttribute("msg","Sucess");
		return "redirect:/codeGroup/list";
	}
	@PostMapping("remove")
	public String remove(CodeGroup codeGroup, RedirectAttributes rttr) throws Exception{
		log.info("post CodeGroup remove");
		service.remove(codeGroup);
		rttr.addFlashAttribute("msg","Sucess");
		return "redirect:/codeGroup/list";
		
	}
}
