package com.example.demo.ems.domain.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class RegistForm {

	private String empPass;
	
	private String empName;
	
	private int gender;
	
	private String address;
	
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date birthday;
	
	private String role;
	
	private int deptId;
}
