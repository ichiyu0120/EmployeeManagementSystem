package com.example.demo.ems.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.ems.domain.model.Emp;

@Mapper
public interface EmpMapper {

	// 全件検索
	public List<Emp> selectAll();

	// 1件取得
	public Emp selectOne(int empId);

	// 新規登録
	public int insert(Emp emp);

	// 更新
	public int update(Emp emp);

	// 削除
	public int delete(int empId);

	// 社員名検索
	public List<Emp> nameSearch(String empName);

	// 部署名検索
	public List<Emp> deptSearch(int deptId);
}
