package com.example.demo.ems.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class RegistForm {

	private String empPass;
	
	private String empName;
	
	private int gender;
	
	private String address;
	
	private Date birthday;
	
	private String role;
	
	private int deptId;
}
