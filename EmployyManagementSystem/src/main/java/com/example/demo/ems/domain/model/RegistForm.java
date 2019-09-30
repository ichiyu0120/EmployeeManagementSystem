package com.example.demo.ems.domain.model;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class RegistForm {

	@NotBlank(groups=ValidGroup1.class)
	@Length(min=4,max=16,groups=ValidGroup2.class)
	private String empPass;
	
	@NotBlank(groups=ValidGroup1.class)
	@Length(min=1,max=30,groups=ValidGroup2.class)
	private String empName;
	
	@NotNull(groups=ValidGroup1.class)
	@Min(value=1,groups=ValidGroup2.class)
	@Max(value=2,groups=ValidGroup2.class)
	private int gender;
	
	@NotBlank(groups=ValidGroup1.class)
	@Length(min=1,max=60,groups=ValidGroup2.class)
	private String address;
	
	@NotNull(groups=ValidGroup1.class)
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date birthday;
	
	@NotBlank(groups=ValidGroup1.class)
	@Length(min=1,max=20)
	private String role;
	
	@Min(value=1,groups=ValidGroup2.class)
	@Max(value=3,groups=ValidGroup2.class)
	private int deptId;
}
