package com.example.demo.ems.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.ems.domain.repository.mybatis.EmpMapper;

@Transactional
@Service("EmpService")
public class EmpService {
	
	@Autowired
	EmpMapper empMapper;

}
