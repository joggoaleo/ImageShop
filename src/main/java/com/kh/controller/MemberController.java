package com.kh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.common.CodeLabelValue;
import com.kh.domain.Member;
import com.kh.service.CodeLabelValueService;
import com.kh.service.MemberService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("user")
public class MemberController {
	// 스프링 시큐리티의 비밀번호 암호처리기
	@Autowired
	private MemberService service;
	@Autowired
	private CodeLabelValueService labelService;
	//@Bean등록 되어있는 스프링 시큐리티의 비밀번호 암호처리기 
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("registerForm")
	public void registerForm(Member member, Model model) throws Exception{
		log.info("get member registerForm");
		//직업코드 목록을 조회(String groupCode = "A00")하여 뷰에 전달.
		String groupCode = "A00";
		List<CodeLabelValue>jobList =labelService.getCodeList(groupCode);
		model.addAttribute("jobList", jobList);
		model.addAttribute("member",member);
	}
	@PostMapping("register")
	public String register(@Validated Member member,BindingResult result,Model model, RedirectAttributes rttr) throws Exception{
		if(result.hasErrors()) {
			//직업코드 목록을 조회(String groupCode = "A00")하여 뷰에 전달.
			String groupCode = "A00";
			List<CodeLabelValue>jobList =labelService.getCodeList(groupCode);
			model.addAttribute("jobList", jobList);
			model.addAttribute("member",member);
			return "user/registerForm";
		}
		//비밀번호 암호화시켜주기
		String inputPassword = member.getUserPw();
		member.setUserPw(passwordEncoder.encode(inputPassword));
		service.register(member);
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/user/registerSuccess";
	}
	
	@GetMapping("list")
	public void list(Member member, Model model) throws Exception{
		log.info("get member list");
		model.addAttribute("list", service.list());
	}
	
	}

