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

	@NotBlank(message="{require_check}")
	@Length(max=16,message="{length_check}")
	private String empPass;
	
	@NotBlank(message="{require_check}")
	@Length(max=30,message="{length_check}")
	private String empName;
	
	@NotNull(message="{require_check}")
	@Min(value=1,message="{min_check}")
	@Max(value=2,message="{max_check}")
	private int gender;
	
	@NotBlank(message="{require_check}")
	@Length(max=60,message="{length_check}")
	private String address;
	
	@NotNull(message="{require_check}")
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date birthday;
	
	@NotBlank(message="{require_check}")
	@Length(max=20,message="{length_check}")
	private String role;
	
	@Min(value=1,message="{min_check}")
	@Max(value=3,message="{max_check}")
	private int deptId;
}
