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
import com.example.demo.ems.util.Conversion;

@Controller
public class ListController {
	
	@Autowired
	EmpService service;
	
	@Autowired
	Conversion conversion;
	
	//get送信された場合はlist.htmlを持たせてmainDisplayへ
	@GetMapping("/list")
	public String getList(Model model) {
		
		List<Emp> empList = service.selectAll();
		model.addAttribute("empList",empList);
		model.addAttribute("contents","list/list");
		
		return "main/mainDisplay";
	}
	
	//社員名検索
	@PostMapping("/nameSearch")
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
	@PostMapping("/deptSearch")
	public String postDeptSearch(@ModelAttribute SearchForm form,Model model) {
		List<Emp> empList = service.deptSearch(form.getDeptId());
		model.addAttribute("empList",empList);
		model.addAttribute("contents","list/list");
		
		return "main/mainDisplay";
	}
}
