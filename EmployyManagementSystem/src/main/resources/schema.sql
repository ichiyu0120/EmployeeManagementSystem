/** 従業員**/
CREATE TABLE IF NOT EXISTS emp (
	emp_id INT PRIMARY KEY,
	emp_pass VARCHAR(50),
  emp_name VARCHAR(50),
  gender INT,
  address VARCHAR(50),
  birthday DATE,
  role VARCHAR(20),
  dept_id INT,
  enable boolean
);


/* 部署 */
CREATE TABLE IF NOT EXISTS department (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(50)
);