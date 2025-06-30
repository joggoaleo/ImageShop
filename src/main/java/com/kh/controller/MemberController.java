package com.kh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.domain.Member;
import com.kh.service.CodeLabelValueService;
import com.kh.service.MemberService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("member")
public class MemberController {
	// 스프링 시큐리티의 비밀번호 암호처리기
	@Autowired
	private MemberService service;
	@Autowired
	private CodeLabelValueService labelService;
	// 스프링 시큐리티의 비밀번호 암호처리기
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("registerForm")
	public void registerForm(Member member, Model model) throws Exception{
		log.info("get member registerForm");
		String groupCode = "A00";
		model.addAttribute("jobList", labelService.getCodeList(groupCode));
	}
	@PostMapping("register")
	public String register(Member member, RedirectAttributes rttr) throws Exception{
	
		return "redirect:/member/list";
	}
	}

