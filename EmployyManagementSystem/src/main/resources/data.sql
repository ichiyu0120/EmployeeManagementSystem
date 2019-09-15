/** 従業員データ**/
INSERT INTO department (dept_id, dept_name) VALUES (1, '管理本部');

INSERT INTO emp (emp_id,emp_pass,emp_name,gender,address,birthday,role,dept_id,enable)
 values (1, 'password', '山田太郎', 1, '東京', '2000-11-01','ROLE_ADMIN', 1, true);

