--edit in 2/7/2024

create database timesheetdb;

USE  timesheetdb;

-- Quản lý vị trí trong dự án
CREATE TABLE positions (
    position_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    shortname VARCHAR(50),
    code VARCHAR(50) UNIQUE NOT NULL
);

-- Quản lý chi nhánh
CREATE TABLE branches (
    branch_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    display_name VARCHAR(255),
    code VARCHAR(50) UNIQUE NOT NULL,
    working_time VARCHAR(255)
);


-- Quản lý người dùng
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(25) NOT NULL,
    password VARCHAR(25) NOT NULL,
    role VARCHAR(10) NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    status ENUM('active', 'inactive') NOT NULL,
    user_type ENUM('staff', 'intern', 'collaborator') NOT NULL,
    level ENUM('intern', 'fresher', 'junior', 'middle', 'senior') NOT NULL,
    position_id INT,
    trainer_id INT,
    branch_id INT,
    FOREIGN KEY (position_id) REFERENCES positions(position_id),
    FOREIGN KEY (trainer_id) REFERENCES users(user_id),
    FOREIGN KEY (branch_id) REFERENCES branches(branch_id)
);


CREATE TABLE permissions (
    permission_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- Quản lý khách hàng
CREATE TABLE customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    address TEXT
);

-- Quản lý nhiệm vụ
CREATE TABLE tasks (
    task_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- Quản lý kiểu nghỉ phép
CREATE TABLE leave_types (
    leave_type_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    num_days INT NOT NULL
);




-- Quản lý ngày nghỉ chung
CREATE TABLE public_holidays (
    holiday_id INT PRIMARY KEY AUTO_INCREMENT,
    note VARCHAR(255),
    period DATE
);

-- Quản lý dự án
CREATE TABLE projects (
    project_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    name VARCHAR(255) NOT NULL,
    project_code VARCHAR(50) UNIQUE NOT NULL,
    start_date DATE,
    end_date DATE,
    note TEXT,
    project_type ENUM('Product', 'ODC', 'Fixed price', 'Training') NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE project_users (
    project_user_id INT PRIMARY KEY AUTO_INCREMENT,
    project_id INT,
    user_id INT,
    position_id INT,
    FOREIGN KEY (project_id) REFERENCES projects(project_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (position_id) REFERENCES positions(position_id)
);

-- Bảng timesheet cá nhân
CREATE TABLE timesheets (
    timesheet_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    project_id INT,
    task_id INT,
    note TEXT,
    working_time FLOAT,
    submit_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('pending', 'approved', 'rejected') DEFAULT 'pending',
    
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (project_id) REFERENCES projects(project_id),
    FOREIGN KEY (task_id) REFERENCES tasks(task_id)
);

-- Quản lý yêu cầu nghỉ phép
CREATE TABLE leave_requests (
    leave_request_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    leave_type_id INT,
    request_date DATE,
    status ENUM('pending', 'approved', 'rejected') DEFAULT 'pending',
    note TEXT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (leave_type_id) REFERENCES leave_types(leave_type_id)
);



CREATE TABLE project_tasks (
    project_task_id INT PRIMARY KEY AUTO_INCREMENT,
    project_id INT,
    task_id INT,
    FOREIGN KEY (project_id) REFERENCES projects(project_id),
    FOREIGN KEY (task_id) REFERENCES tasks(task_id)
);
