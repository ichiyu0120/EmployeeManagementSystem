package com.example.demo.ems.domain.repository.mybatis;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.ems.domain.model.Emp;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmpMapperTest {

	@Autowired
	private EmpMapper empMapper;
	
	@Test
	public void testSelectAll() {
		Emp entity = new Emp();
		entity.setDeptId(1);
		Emp actual = empMapper.selectOne(3);
		
		assertNotNull(actual);
		assertThat(actual.getEmpId(), is(3));
		assertThat(actual.getEmpPass(), is("3333"));
		
	}
}
