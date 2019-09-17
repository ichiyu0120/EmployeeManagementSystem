package com.example.demo.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.ems.domain.model.Emp;
import com.example.demo.ems.domain.model.SearchForm;
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
		
		List<Emp> empList = service.selectAll();
		model.addAttribute("empList",empList);
		model.addAttribute("contents","list/list");
		
		return "main/mainDisplay";
	}
	
	//社員名検索
	@PostMapping("/nameSearchCtrl")
	public String postNameSearch(@ModelAttribute SearchForm form,Model model) {
		if(form.getName().length() == 0) {
			List<Emp> empList = service.selectAll();
			model.addAttribute("empList",empList);
		}else {
			List<Emp> empList = service.nameSearch(form.getName());
			model.addAttribute("empList",empList);
		}
		
		model.addAttribute("contents","list/list");
		
		return "main/mainDisplay";
	}
	
	//部署名検索
	@PostMapping("/deptSearchCtrl")
	public String postDeptSearch(@ModelAttribute SearchForm form,Model model) {
		List<Emp> empList = service.deptSearch(form.getDeptId());
		model.addAttribute("empList",empList);
		model.addAttribute("contents","list/list");
		
		return "main/mainDisplay";
	}
}
