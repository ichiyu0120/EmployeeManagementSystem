package com.example.demo.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	//アプリケーションのスタート
	//get送信された場合index.htmlへ
	@GetMapping("/index")
	public String getIndex() {
		return "main/index";
	}
	
}
