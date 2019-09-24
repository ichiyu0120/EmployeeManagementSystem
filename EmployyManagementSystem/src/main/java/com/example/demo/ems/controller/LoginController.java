package com.example.demo.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String getLogin() {
		
		return "main/index";
	}

	@GetMapping("/logout")
	public String getLogout() {
		
		return "redirect:/index";
	}
	
	@PostMapping("/logout")
	public String postLogout() {
		
		return "redirect:/index";
	}
}
