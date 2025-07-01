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
	// @Bean등록 되어있는 스프링 시큐리티의 비밀번호 암호처리기
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("registerForm")
	public void registerForm(Member member, Model model) throws Exception {
		log.info("get member registerForm");
		// 직업코드 목록을 조회(String groupCode = "A00")하여 뷰에 전달.
		String groupCode = "A00";
		List<CodeLabelValue> jobList = labelService.getCodeList(groupCode);
		model.addAttribute("jobList", jobList);
		model.addAttribute("member", member);
	}

	@PostMapping("register")
	public String register(@Validated Member member, BindingResult result, Model model, RedirectAttributes rttr)
			throws Exception {
		System.out.println("입력된 Member: " + member);
		if (result.hasErrors()) {
			// 직업코드 목록을 조회(String groupCode = "A00")하여 뷰에 전달.
			String groupCode = "A00";
			List<CodeLabelValue> jobList = labelService.getCodeList(groupCode);
			model.addAttribute("jobList", jobList);
			model.addAttribute("member", member);
			return "user/registerForm";
		}
		// 비밀번호 암호화시켜주기
		String inputPassword = member.getUserPw();
		member.setUserPw(passwordEncoder.encode(inputPassword));
		service.register(member);
		rttr.addFlashAttribute("userName", member.getUserName());
		return "redirect:/user/registerSuccess";
	}

	@GetMapping("registerSuccess")
	public void registerSucess(Member mebmer) {

	}

	@GetMapping("list")
	public void list(Member member, Model model) throws Exception {
		log.info("get member list");
		model.addAttribute("list", service.list());
	}

	@GetMapping("read") // member의 세터스로 자동 맵핑
	public void read(Member member, Model model) throws Exception {
		String groupCode = "A00";
		List<CodeLabelValue> jobList = labelService.getCodeList(groupCode);
		model.addAttribute("jobList", jobList);
		model.addAttribute("member",service.read(member));
	}

	// 수정 페이지
	@GetMapping("/modifyForm")
	public void modifyForm(Member member, Model model) throws Exception {
		// 직업코드 목록을 조회하여 뷰에 전달
		String groupCode = "A00";
		List<CodeLabelValue> jobList = labelService.getCodeList(groupCode);
		model.addAttribute("jobList", jobList);
		model.addAttribute("member",service.read(member));
	}

	@PostMapping("modify")
	public String modify(Member member, RedirectAttributes rttr) throws Exception {
		service.modify(member);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/user/list";
	}

	// 삭제 처리
	@PostMapping("/remove")
	public String remove(Member member, RedirectAttributes rttr) throws Exception {
		service.remove(member.getUserNo());
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/user/list";
	}

	// 최초 관리자를 생성하는 화면을 반환한다.
	@GetMapping("/setup")
	public String setupAdminForm(Member member, Model model) throws Exception {
		// 회원 테이블 데이터 건수를 확인하여 최초 관리자 등록 페이지를 표시한다.
		if (service.countAll() == 0) {
			return "user/setup";
		}
		// 회원 테이블에 데이터가 존재하면 최초 관리자를 생성할 수 없으므로 실패 페이지로 이동한다.

		return "user/setupFailure";
	}

	// 회원 테이블에 데이터가 없으면 최초 관리자를 생성한다.
	@PostMapping("/setup")
	public String setupAdmin(Member member, RedirectAttributes rttr) throws Exception {
		// 회원 테이블 데이터 건수를 확인하여 빈 테이블이면 최초 관리자를 생성한다.
		if (service.countAll() == 0) {
			String inputPassword = member.getUserPw();
			member.setUserPw(passwordEncoder.encode(inputPassword));
			member.setJob("00");
			service.setupAdmin(member);
			rttr.addFlashAttribute("userName", member.getUserName());
			return "redirect:/user/registerSuccess";
		}
		// 회원 테이블에 데이터가 존재하면 최초 관리자를 생성할 수 없으므로 실패 페이지로 이동한다.

		return "redirect:/user/setupFailure";
	}
	//로그인 실패 에러페이지
	@GetMapping("accessError")
	public void accessErrorForm(Model model) throws Exception {
	}
}
