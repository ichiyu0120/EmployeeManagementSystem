package com.example.demo.ems.domain.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.ems.domain.model.Department;

@Mapper
public interface DepartmentMapper {

	Department selectOne(Department entity);
}
