package com.example.demo.ems.domain.repository.mybatis;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.ems.domain.model.Department;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Slf4j
public class DepartmentMapperTest {

	@Autowired
	private DepartmentMapper departmentMapper;

	@Test
	public void testSelectOne() {

		Department entity = new Department();
		entity.setDeptId(1);
		Department actual = departmentMapper.selectOne(entity);
		log.debug("actual:{}", actual);
		
		assertNotNull(actual);
		assertThat(actual.getDeptId(), is(1));
		assertThat(actual.getDeptName(), is("管理本部"));

		
		
	}

}
