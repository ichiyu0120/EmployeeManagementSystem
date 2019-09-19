package com.example.demo.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.ems.domain.model.Emp;
import com.example.demo.ems.domain.service.EmpService;
import com.example.demo.ems.util.Conversion;

@Controller
public class DeleteController {

	@Autowired
	EmpService service;
	
	@Autowired
	Conversion conversion;
	
	//削除確認画面へ遷移
	@PostMapping("/deleteCheck")
	public String postDeleteCheck(@RequestParam("empId") int empId,Model model) {
		
		Emp emp = service.selectOne(empId);
		model.addAttribute("emp",emp);
		model.addAttribute("contents","delete/deleteCheck");
		
		return "main/mainDisplay";
	}
	
	//削除処理を行い完了画面へ遷移
	@PostMapping("/deleteComplete")
	public String postDeleteComplete(@RequestParam("empId") int empId,Model model) {
		
		int result = service.delete(empId);
		
		String message;
		if(0 < result) {
			message = "削除処理が終了しました。";
		}else {
			message = "処理に失敗しました。";
		}
		
		model.addAttribute("message", message);
		model.addAttribute("contents","delete/deleteComplete");
		
		return "main/mainDisplay";
	}
	
}
