/** 従業員データ**/
INSERT INTO department (dept_id, dept_name) VALUES (1, '営業部');
INSERT INTO department (dept_id, dept_name) VALUES (2, '経理部');
INSERT INTO department (dept_id, dept_name) VALUES (3, '総務部');

INSERT INTO emp (emp_id,emp_pass,emp_name,gender,address,birthday,role,dept_id,enable)
 values (1, '$2a$10$NFsNP4211JOrYA5tpd2GHejUigEOO6K47qC8texXikLc8ZNAHIiIK', '山田太郎', 1, '東京', '2000-11-01','ROLE_ADMIN', 1, true);

