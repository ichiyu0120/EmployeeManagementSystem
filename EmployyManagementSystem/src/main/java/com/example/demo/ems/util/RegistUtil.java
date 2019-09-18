package com.example.demo.ems.util;

import org.springframework.stereotype.Service;

import com.example.demo.ems.domain.model.Emp;
import com.example.demo.ems.domain.model.RegistForm;

@Service
public class RegistUtil {

	// 新規登録画面用にEmpを初期化
	public Emp empInitialCreate() {
		Emp emp = new Emp();
		emp.setEmpPass(null);
		emp.setEmpName(null);
		emp.setGender(1);
		emp.setAddress(null);
		emp.setBirthday(null);
		emp.setRole("ROLE_GENERAL");
		emp.setDeptId(1);
		emp.setEnable(true);

		return emp;
	}

	//formを元にEmpを作成する
	public Emp createEmpWithForm(RegistForm form) {

		Emp emp = new Emp();
		emp.setEmpPass(form.getEmpPass());
		emp.setEmpName(form.getEmpName());
		emp.setGender(form.getGender());
		emp.setAddress(form.getAddress());
		emp.setBirthday(form.getBirthday());
		emp.setRole(form.getRole());
		emp.setDeptId(form.getDeptId());
		emp.setEnable(true);

		return emp;
	}

}
