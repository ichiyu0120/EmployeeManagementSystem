package com.example.demo.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.ems.domain.service.EmpService;

@Controller
public class ListController {
	
	@Autowired
	EmpService service;
	
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
