package com.example.demo.ems.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.ems.domain.model.Department;
import com.example.demo.ems.domain.model.Emp;
import com.example.demo.ems.domain.repository.EmpDAO;

@Repository("EmpDAOJdbc")
public class EmpDAOJdbc implements EmpDAO {
	
	@Autowired
	JdbcTemplate jdbc;

	//件数取得
	@Override
	public int count() throws DataAccessException {
		//SQL文
		String sql = "SELECT COUNT(*) FROM emp";
		
		int count = jdbc.queryForObject(sql, Integer.class);
		return count;
	}

	//全件取得
	@Override
	public List<Emp> selectAll() throws DataAccessException {
		//SQL文
		String sql = "SELECT"
						+ " emp_id,"
						+ " emp_pass,"
						+ " emp_name,"
						+ " gender,"
						+ " address,"
						+ " birthday,"
						+ " role,"
						+ " e.dept_id,"
						+ " enable,"
						+ " dept_name "
						+ "FROM"
						+ " emp e "
						+ "INNER JOIN"
						+ " department d "
						+ "ON"
						+ " e.dept_id = d.dept_id";
		
		//MapにEmpデータを取得して格納
		List<Map<String,Object>> getList = jdbc.queryForList(sql);
		
		//Mapのデータを1件ずつ結果返却Listに格納する
		List<Emp> empList = new ArrayList<>();
		for(Map<String,Object> map : getList) {
			Emp emp = new Emp();
			emp.setEmpId((Integer)map.get("emp_id"));
			emp.setEmpPass((String)map.get("emp_pass"));
			emp.setEmpName((String)map.get("emp_name"));
			emp.setGender((Integer)map.get("gender"));
			emp.setAddress((String)map.get("address"));
			emp.setBirthday((Date)map.get("birthday"));
			emp.setRole((String)map.get("role"));
			
			Department dept = new Department();
			dept.setDeptId((Integer)map.get("e.dept_id"));
			dept.setDeptName((String)map.get("dept_name"));
			emp.setDept(dept);
			
			empList.add(emp);
		}
		
		return empList;
	}

	//1件取得
	@Override
	public Emp selectOne(int empId) throws DataAccessException {
		//SQL文
		String sql = "SELECT"
					+ " emp_id,"
					+ " emp_pass,"
					+ " emp_name,"
					+ " gender,"
					+ " address,"
					+ " birthday,"
					+ " role,"
					+ " e.dept_id,"
					+ " enable,"
					+ " dept_name "
					+ "FROM"
					+ " emp e "
					+ "INNER JOIN"
					+ " department d "
					+ "ON"
					+ " e.dept_id = d.dept_id "
					+ "WHERE"
					+ " emp_id = ?";
		
		//検索結果のMapをEmpに格納
		Map<String,Object> map = jdbc.queryForMap(sql,empId);
		
		Emp emp = new Emp();
		emp.setEmpId((Integer)map.get("emp_id"));
		emp.setEmpPass((String)map.get("emp_pass"));
		emp.setEmpName((String)map.get("emp_name"));
		emp.setGender((Integer)map.get("gender"));
		emp.setAddress((String)map.get("address"));
		emp.setBirthday((Date)map.get("birthday"));
		emp.setRole((String)map.get("role"));
		
		Department dept = new Department();
		dept.setDeptId((Integer)map.get("e.dept_id"));
		dept.setDeptName((String)map.get("dept_name"));
		emp.setDept(dept);
		
		return emp;
	}

	//新規登録
	@Override
	public int insert(Emp emp) throws DataAccessException {
		//SQL文
		String sql = "INSERT INTO emp VALUES(?,?,?,?,?,?,?,?)";
		
		//SQL実行結果の件数を取得
		int rowNumber = jdbc.update(sql,
				emp.getEmpPass(),
				emp.getEmpName(),
				emp.getGender(),
				emp.getAddress(),
				emp.getBirthday(),
				emp.getRole(),
				emp.getDept().getDeptId(),
				"true");
		
		return rowNumber;
	}
	
	//更新
	@Override
	public int update(Emp emp) throws DataAccessException {
		//SQL文
		String sql = "UPDATE"
					+ " emp "
					+ "SET"
					+ " emp_pass=?,"
					+ " emp_name=?,"
					+ " gender=?,"
					+ " address=?,"
					+ " birthday=?,"
					+ " role=?,"
					+ " dept_id=? "
					+ "WHERE"
					+ " emp_id=?";
		
		//SQL実行結果の件数を取得
		int rowNumber = jdbc.update(sql,
				emp.getEmpPass(),
				emp.getEmpName(),
				emp.getGender(),
				emp.getAddress(),
				emp.getBirthday(),
				emp.getRole(),
				emp.getDept().getDeptId(),
				emp.getEmpId());
		
		return rowNumber;
	}

	//削除
	@Override
	public int delete(int empId) throws DataAccessException {
		//SQL文
		String sql = "DELETE FROM"
							+ " emp "
							+ "WHERE"
							+ " emp_id=?";
		
		//SQL実行結果の件数を取得
		int rowNumber = jdbc.update(sql,empId);
		
		return rowNumber;
	}

	//社員名あいまい検索
	@Override
	public List<Emp> nameSearch(String empName) throws DataAccessException {
		//SQL文
		String sql = "SELECT"
				+ " emp_id,"
				+ " emp_pass,"
				+ " emp_name,"
				+ " gender,"
				+ " address,"
				+ " birthday,"
				+ " role,"
				+ " e.dept_id,"
				+ " enable,"
				+ " dept_name "
				+ "FROM"
				+ " emp e "
				+ "INNER JOIN"
				+ " department d "
				+ "ON"
				+ " e.dept_id = d.dept_id "
				+ "WHERE"
				+ " emp_name "
				+ "LIKE"
				+ " ? ";
		
		//プレースホルダーにセットする値
		String placeHolder = "%"+empName+"%";
		
		//MapにEmpデータを取得して格納
		List<Map<String,Object>> getList = jdbc.queryForList(sql,placeHolder);
		
		//Mapのデータを1件ずつ結果返却Listに格納する
		List<Emp> empList = new ArrayList<>();
		for(Map<String,Object> map : getList) {
			Emp emp = new Emp();
			emp.setEmpId((Integer)map.get("emp_id"));
			emp.setEmpPass((String)map.get("emp_pass"));
			emp.setEmpName((String)map.get("emp_name"));
			emp.setGender((Integer)map.get("gender"));
			emp.setAddress((String)map.get("address"));
			emp.setBirthday((Date)map.get("birthday"));
			emp.setRole((String)map.get("role"));
			
			Department dept = new Department();
			dept.setDeptId((Integer)map.get("e.dept_id"));
			dept.setDeptName((String)map.get("dept_name"));
			emp.setDept(dept);
			
			empList.add(emp);
		}
				
		return empList;
	}

	//部署名検索
	@Override
	public List<Emp> deptSearch(int deptId) throws DataAccessException {
		//SQL文
		String sql = "SELECT"
				+ " emp_id,"
				+ " emp_pass,"
				+ " emp_name,"
				+ " gender,"
				+ " address,"
				+ " birthday,"
				+ " role,"
				+ " e.dept_id,"
				+ " enable,"
				+ " dept_name "
				+ "FROM"
				+ " emp e "
				+ "INNER JOIN"
				+ " department d "
				+ "ON"
				+ " e.dept_id = d.dept_id "
				+ "WHERE"
				+ " e.dept_id = ?";
		
		//MapにEmpデータを取得して格納
		List<Map<String,Object>> getList = jdbc.queryForList(sql,deptId);
		
		//Mapのデータを1件ずつ結果返却Listに格納する
		List<Emp> empList = new ArrayList<>();
		for(Map<String,Object> map : getList) {
			Emp emp = new Emp();
			emp.setEmpId((Integer)map.get("emp_id"));
			emp.setEmpPass((String)map.get("emp_pass"));
			emp.setEmpName((String)map.get("emp_name"));
			emp.setGender((Integer)map.get("gender"));
			emp.setAddress((String)map.get("address"));
			emp.setBirthday((Date)map.get("birthday"));
			emp.setRole((String)map.get("role"));
			
			Department dept = new Department();
			dept.setDeptId((Integer)map.get("e.dept_id"));
			dept.setDeptName((String)map.get("dept_name"));
			emp.setDept(dept);
			
			empList.add(emp);
		}
		
		return empList;
		
	}

}
