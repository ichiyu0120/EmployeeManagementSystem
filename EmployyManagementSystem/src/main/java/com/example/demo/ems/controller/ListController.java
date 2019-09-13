package com.example.demo.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ListController {
	
	//get送信された場合はindexに戻す
	@GetMapping("/listCtrl")
	public String getList() {
		return "redirect:/index";
	}
	
	//post送信された場合はlist.htmlを持たせてhomeLayoutへ
	@PostMapping("/listCtrl")
	public String postList(Model model) {
		
		model.addAttribute("contents","list/list");
		
		return "main/mainDisplay";
	}
}
