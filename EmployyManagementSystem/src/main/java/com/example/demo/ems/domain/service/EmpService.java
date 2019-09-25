package com.example.demo.ems.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.ems.domain.model.Emp;
import com.example.demo.ems.domain.repository.mybatis.EmpMapper;

@Transactional
@Service("EmpService")
public class EmpService {
	
	@Autowired
	EmpMapper empMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	//全件検索
	public List<Emp> selectAll(){
		
		return empMapper.selectAll();
	}

	//1件取得
	public Emp selectOne(int empId) {
		
		return empMapper.selectOne(empId);
	}
	
	//新規登録
	public int insert(Emp emp) {
		
		String password = passwordEncoder.encode(emp.getEmpPass());
		emp.setEmpPass(password);
		
		return empMapper.insert(emp);
	}
	
	//更新
	public int update(Emp emp) {
		
		String password = passwordEncoder.encode(emp.getEmpPass());
		emp.setEmpPass(password);
		
		return empMapper.update(emp);
	}
	
	//削除
	public int delete(int empId) {
		
		return empMapper.delete(empId);
	}
	
	//社員名検索
	public List<Emp> nameSearch(String name){
		
		String empName = "%"+name+"%";
		return empMapper.nameSearch(empName);
		
	}
	
	//部署名検索
	public List<Emp> deptSearch(int deptId){
	
		return empMapper.deptSearch(deptId);
	}
	
}
