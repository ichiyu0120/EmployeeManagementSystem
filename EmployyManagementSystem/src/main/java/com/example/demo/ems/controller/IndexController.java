package com.example.demo.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.ems.domain.model.Emp;
import com.example.demo.ems.domain.model.PasswordResetForm;
import com.example.demo.ems.domain.service.EmpService;
import com.example.demo.ems.util.RegistUtil;

@Controller
public class IndexController {
	
	@Autowired
	RegistUtil util;
	
	@Autowired
	EmpService service;
	

	//アプリケーションのスタート
	//get送信された場合index.htmlへ
	@GetMapping("/index")
	public String getIndex() {
		return "main/index";
	}
	
	//パスワード再設定フォームへ遷移
	@GetMapping("/passwordReset")
	public String getPasswordReset() {
		return "main/passwordResetForm";
	}
	
	//再設定のチェック
	@PostMapping("/passwordResetCheck")
	public String postPasswordResetCheck(@ModelAttribute PasswordResetForm form,Model model) {
		
		Emp emp = util.createEmpWithForm(form);
		if(service.idAndNameSearch(emp) == null) {
			String message = "データが存在しません。";
			model.addAttribute("resetMessage",message);
			
			return "main/passwordResetForm";
			
		}else {
			String secretPassword = "";
			for(int i = 0; i < form.getEmpPass().length(); i++) {
				secretPassword += "＊";
			}
			model.addAttribute("secretPassword",secretPassword);
			model.addAttribute("emp",emp);
			
			return "main/passwordResetCheck";
		}
	}
	
	@PostMapping("/passwordResetComplete")
	public String postPasswordResetComplete(@ModelAttribute PasswordResetForm form,Model model) {
		
		Emp emp = util.createEmpWithForm(form);
		
		int result = service.passwordUpdate(emp);
		
		String message;
		if(result == 0) {
			message = "更新処理に失敗しました。";
		}else {
			message = "パスワードを再設定しました。";
		}

		model.addAttribute("message",message);
		
		return "main/passwordResetComplete";
	}
	
}
