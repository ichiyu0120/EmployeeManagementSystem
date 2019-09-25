package com.example.demo.ems.domain.model;

import lombok.Data;

@Data
public class PasswordResetForm {
	
	private int empId;
	
	private String empName;
	
	private String empPass;

}
