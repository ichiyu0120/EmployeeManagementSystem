package com.example.demo.ems.domain.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class PasswordResetForm {
	
	@NotNull(groups=ValidGroup1.class)
	@Min(value=1,groups=ValidGroup2.class)
	@Max(value=99999,groups=ValidGroup2.class)
	private int empId;
	
	@NotBlank(groups=ValidGroup1.class)
	@Length(min=1,max=30,groups=ValidGroup2.class)
	private String empName;
	
	@NotBlank(groups=ValidGroup1.class)
	@Length(min=4,max=16,groups=ValidGroup2.class)
	private String empPass;

}
