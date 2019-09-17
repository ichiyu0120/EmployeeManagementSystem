package com.example.demo.ems.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.ems.domain.model.Emp;
import com.example.demo.ems.domain.repository.mybatis.EmpMapper;

@Transactional
@Service("EmpService")
public class EmpService {
	
	@Autowired
	EmpMapper empMapper;
	
	//全件検索
	public List<Emp> selectAll(){
		
		List<Emp> empList = empMapper.selectAll();

		//権限と性別を表示させたい値にif文で変更
		for(Emp emp:empList){
			if(emp.getRole().equals("ROLE_ADMIN")) {
				emp.setRole("管理者");
			}else if(emp.getRole().equals("ROLE_GENERAL")) {
				emp.setRole("一般");
			}else {
				emp.setRole("不明");
			}
			
			if(emp.getGender() == 1) {
				emp.setStrGender("男性");
			}else if(emp.getGender() == 2) {
				emp.setStrGender("女性");
			}else {
				emp.setStrGender("不明");
			}
		}
		return empList;
	}

	//1件取得
	public Emp selectOne(int empId) {
		
		return empMapper.selectOne(empId);
	}
	
	//新規登録
	public int insert(Emp emp) {
		
		return empMapper.insert(emp);
	}
	
	//更新
	public int update(Emp emp) {
		
		return empMapper.update(emp);
	}
	
	//削除
	public int delete(int empId) {
		
		return empMapper.delete(empId);
	}
	
	//社員名検索
	public List<Emp> nameSearch(String name){
		
		String empName = "%"+name+"%";
		List<Emp> empList = empMapper.nameSearch(empName);
		
		for(Emp emp:empList){
			if(emp.getRole().equals("ROLE_ADMIN")) {
				emp.setRole("管理者");
			}else if(emp.getRole().equals("ROLE_GENERAL")) {
				emp.setRole("一般");
			}else {
				emp.setRole("不明");
			}
			
			if(emp.getGender() == 1) {
				emp.setStrGender("男性");
			}else if(emp.getGender() == 2) {
				emp.setStrGender("女性");
			}else {
				emp.setStrGender("不明");
			}
		}
		return empList;
	}
	
	//部署名検索
	public List<Emp> deptSearch(int deptId){
	
		return empMapper.deptSearch(deptId);
	}
}
