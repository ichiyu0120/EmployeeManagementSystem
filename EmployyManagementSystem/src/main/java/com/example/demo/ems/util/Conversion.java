package com.example.demo.ems.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class Conversion {
	
	//権限を値に応じてStringに変換するメソッド
	public String roleConversion(String role) {
		
		Map<String,String> map = new HashMap<>();
		
		map.put("ROLE_ADMIN", "管理者");
		map.put("ROLE_GENERAL", "一般");
		
		String convRole = map.get(role);
		
		return convRole;
	}

	//性別を値に応じてStringに変換するメソッド
	public String genderConversion(int gender) {
		
		Map<Integer, String> map = new HashMap<>();
		
		map.put(1, "男性");
		map.put(2, "女性");
		
		String strGender = map.get(gender);
		
		return strGender;
	}
	
	//部署IDを対応した部署名に変換するメソッド
	public String deptConversion(int deptId) {
		
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "営業部");
		map.put(2, "経理部");
		map.put(3, "総務部");
		
		String deptName = map.get(deptId);
		
		return deptName;

	}
	
	
}
