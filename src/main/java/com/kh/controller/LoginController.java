package com.kh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {
// 로그인 페이지 
	@GetMapping("/login")
	public String loginForm(String error, String logout, Model model) {
		if (error != null) {
			model.addAttribute("error", "로그인 에러!!!");
		}
		if (logout != null) {
			model.addAttribute("logout", "로그아웃!!!");
		}
		return "auth/loginForm";
	}

	// 로그아웃 페이지를 생성한다.
	@GetMapping("/logout")
	public String logoutForm() {
		return "auth/logoutForm";
	}
}