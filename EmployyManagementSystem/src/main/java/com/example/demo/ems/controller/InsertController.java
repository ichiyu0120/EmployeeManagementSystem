package com.example.demo.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.ems.domain.model.Emp;
import com.example.demo.ems.domain.model.RegistForm;
import com.example.demo.ems.domain.service.EmpService;
import com.example.demo.ems.util.Conversion;
import com.example.demo.ems.util.RegistUtil;

@Controller
public class InsertController {

	@Autowired
	Conversion conversion;
	
	@Autowired
	RegistUtil util;
	
	@Autowired
	EmpService service;
	
	//新規登録入力画面へ遷移
	@GetMapping("/insertInput")
	public String getInsertInput(@ModelAttribute RegistForm form,Model model) {
		
		Emp emp = util.empInitialCreate();
		model.addAttribute("emp",emp);
		model.addAttribute("contents", "insert/inputForm");
		
		return "main/mainDisplay";
	}
	
	//登録確認画面へ遷移
	@PostMapping("/insertCheck")
	public String postInsertcheck(@ModelAttribute @Validated RegistForm form,BindingResult bindingResult,Model model) {
		
		Emp emp = util.createEmpWithForm(form);
		model.addAttribute("emp",emp);
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("contents", "insert/inputForm");
			return "main/mainDisplay";
		}
		
		model.addAttribute("contents","insert/checkForm");
		
		return "main/mainDisplay";
	}
	
	//確認画面から登録画面へEmpを持って戻す
	@PostMapping("/insertReturn")
	public String postInsertReturn(@ModelAttribute RegistForm form,Model model) {
		
		Emp emp = util.createEmpWithForm(form);
		
		model.addAttribute("emp",emp);
		model.addAttribute("contents", "insert/inputForm");
		
		return "main/mainDisplay";
	}
	
	//登録処理を行い、完了画面へ遷移
	@PostMapping("/insertComplete")
	public String insertComplete(@ModelAttribute RegistForm form,Model model) {
		
		Emp emp = util.createEmpWithForm(form);
		
		int result = service.insert(emp);
		String message;
		if(0 < result) {
			message =  "社員登録処理が終了しました。";
		}else {
			message = "処理に失敗しました。";
		}
		
		model.addAttribute("message",message);
		model.addAttribute("contents", "insert/complete");
		
		return "main/mainDisplay";
		
		
	}
	
}
