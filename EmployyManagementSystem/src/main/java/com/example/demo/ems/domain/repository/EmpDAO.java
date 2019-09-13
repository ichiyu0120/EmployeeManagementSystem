package com.example.demo.ems.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.ems.domain.model.Emp;

public interface EmpDAO {
	
	//件数取得
	public int count() throws DataAccessException;
	
	//全件検索
	public List<Emp> selectAll() throws DataAccessException;
	
	//1件取得
	public Emp selectOne(int empId) throws DataAccessException;
	
	//新規登録
	public int insert(Emp emp) throws DataAccessException;
	
	//更新
	public int update(Emp emp) throws DataAccessException;
	
	//削除
	public int delete(int empId) throws DataAccessException;
	
	//社員名検索
	public List<Emp> nameSearch(String empName) throws DataAccessException;
	
	//部署名検索
	public List<Emp> deptSearch(int deptId) throws DataAccessException;

}
