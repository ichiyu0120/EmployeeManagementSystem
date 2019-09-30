package com.example.demo.ems.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.ems.domain.model.Emp;
import com.example.demo.ems.domain.model.GroupOrder;
import com.example.demo.ems.domain.model.RegistForm;
import com.example.demo.ems.domain.model.SessionEmp;
import com.example.demo.ems.domain.service.EmpService;
import com.example.demo.ems.util.Conversion;
import com.example.demo.ems.util.RegistUtil;

@Controller
public class UpdateController {

	@Autowired
	EmpService service;
	
	@Autowired
	Conversion conversion;
	
	@Autowired
	RegistUtil util;
	
	@Autowired
	HttpSession session;
	
	//empIdの該当データを取得して入力画面へ
	@PostMapping("/updateInput")
	public String postUpdateInput(@ModelAttribute RegistForm form,@RequestParam("empId")int empId,Model model) {
		
		Emp emp = service.selectOne(empId);
		model.addAttribute("emp",emp);
		model.addAttribute("contents","update/updateInput");
		model.addAttribute("sessionEmp",session.getAttribute("sessionEmp"));		
		return "main/mainDisplay";
	}
	
	//内容確認画面
	@PostMapping("/updateCheck")
	public String postUpdateCheck(@ModelAttribute @Validated(GroupOrder.class) RegistForm form,BindingResult bindingResult,@RequestParam("empId")int empId,Model model) {
		
		Emp emp = util.createEmpWithForm(form);
		emp.setEmpId(empId);
		model.addAttribute("emp",emp);
		model.addAttribute("sessionEmp",session.getAttribute("sessionEmp"));
		if(bindingResult.hasErrors()) {
			model.addAttribute("contents","update/updateInput");
			return "main/mainDisplay";
		}
		
		model.addAttribute("contents","update/updateCheck");
		return "main/mainDisplay";
	}
	
	//変更しようとした値を持たせて入力画面へ
	@PostMapping("/updateReturn")
	public String postUpdateReturn(@ModelAttribute RegistForm form,@RequestParam("empId")int empId,Model model) {
		
		Emp emp = util.createEmpWithForm(form);
		emp.setEmpId(empId);
		model.addAttribute("emp",emp);
		model.addAttribute("contents","update/updateInput");
		model.addAttribute("sessionEmp",session.getAttribute("sessionEmp"));
		return "main/mainDisplay";
	}
	
	//変更処理を行い完了画面へ
	@PostMapping("/updateComplete")
	public String postUpdateComplete(@ModelAttribute RegistForm form,@RequestParam("empId")int empId,Model model) {
		
		Emp emp = util.createEmpWithForm(form);
		emp.setEmpId(empId);
		int result = service.update(emp);
		String message;
		if(0 < result) {
			message =  "社員更新処理が終了しました。";
		}else {
			message = "処理に失敗しました。";
		}
		
		SessionEmp sessionEmp = (SessionEmp)session.getAttribute("sessionEmp");
		int sessionEmpId = sessionEmp.getEmpId();
		if(empId == sessionEmpId) {
			sessionEmp.setEmpName(emp.getEmpName());
			session.setAttribute("sessionEmp", sessionEmp);
		}
		
		model.addAttribute("message",message);
		model.addAttribute("contents", "update/updateComplete");
		model.addAttribute("sessionEmp",sessionEmp);
		
		return "main/mainDisplay";
	}
}
