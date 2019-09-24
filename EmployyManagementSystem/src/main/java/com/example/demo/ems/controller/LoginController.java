package com.example.demo.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	@PostMapping("/logout")
	public String postLogout() {
		
		return "redirect:/index";
	}
}
