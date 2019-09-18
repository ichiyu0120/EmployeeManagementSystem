package com.example.demo.ems.util;

import org.springframework.stereotype.Service;

@Service
public class Conversion {
	
	//権限を値に応じてStringに変換するメソッド
	public String roleConversion(String role) {
		
		String convRole;
		
		if(role.equals("ROLE_ADMIN")) {
			convRole = "管理者";
		}else if(role.equals("ROLE_GENERAL")) {
			convRole = "一般";
		}else {
			convRole = "不明";
		}
		
		return convRole;
	}

	//性別を値に応じてStringに変換するメソッド
	public String genderConversion(int gender) {
		
		String strGender;
		
		if(gender == 1) {
			strGender = "男性";
		}else if(gender == 2) {
			strGender = "女性";
		}else {
			strGender = "不明";
		}
		
		return strGender;
	}
	
	//部署IDを対応した部署名に変換するメソッド
	public String deptConversion(int deptId) {
		
		String deptName;
		
		if(deptId == 1) {
			deptName = "営業部";
		}else if(deptId == 2) {
			deptName = "経理部";
		}else if(deptId ==3){
			deptName = "総務部";
		}else {
			deptName = "不明";
		}
		
		return deptName;

	}
}