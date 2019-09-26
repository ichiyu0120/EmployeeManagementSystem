package com.example.demo.ems.domain.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class PasswordResetForm {
	
	@NotNull(message="{require_check}")
	@Min(value=1,message="{min_check}")
	@Max(value=99999,message="{max_check}")
	private int empId;
	
	@NotBlank(message="{require_check}")
	@Length(max=30,message="{length_check}")
	private String empName;
	
	@NotBlank(message="{require_check}")
	@Length(max=16,message="{length_check}")
	private String empPass;

}
