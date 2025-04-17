CREATE DATABASE IF NOT EXISTS student_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE student_db;
-- 用户表
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    real_name VARCHAR(50),
    gender VARCHAR(10),
    birth_date DATE,
    phone VARCHAR(20),
    email VARCHAR(100),
    address TEXT,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 学历信息表
CREATE TABLE IF NOT EXISTS educations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    school_name VARCHAR(100) NOT NULL,
    education_level VARCHAR(50),
    major VARCHAR(100),
    start_date DATE,
    end_date DATE,
    description TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 课程信息表
CREATE TABLE IF NOT EXISTS courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    course_name VARCHAR(100) NOT NULL,
    teacher VARCHAR(50),
    score DECIMAL(5,2),
    credit DECIMAL(3,1),
    semester VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 获奖信息表
CREATE TABLE IF NOT EXISTS awards (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    award_name VARCHAR(100) NOT NULL,
    award_level VARCHAR(50),
    award_date DATE,
    issuing_authority VARCHAR(100),
    description TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
-- 添加用户数据
INSERT INTO users (username, password, real_name, gender, birth_date, phone, email, address, create_time) VALUES
('zhangsan', '123456', '张三', '男', '1998-05-15', '13812345678', 'zhangsan@example.com', '北京市海淀区', NOW()),
('lisi', '123456', '李四', '女', '1999-08-22', '13987654321', 'lisi@example.com', '上海市浦东新区', NOW()),
('wangwu', '123456', '王五', '男', '1997-03-10', '13765432198', 'wangwu@example.com', '广州市天河区', NOW());

-- 添加学历信息数据
INSERT INTO educations (user_id, school_name, education_level, major, start_date, end_date, description) VALUES
(1, '北京大学', '本科', '计算机科学与技术', '2016-09-01', '2020-07-01', '主修计算机科学与技术，辅修数学'),
(1, '清华大学', '硕士', '软件工程', '2020-09-01', '2023-07-01', '研究方向：人工智能与大数据分析'),
(2, '复旦大学', '本科', '金融学', '2017-09-01', '2021-07-01', '主修金融学，辅修经济学'),
(3, '华南理工大学', '本科', '电子信息工程', '2015-09-01', '2019-07-01', '主要研究嵌入式系统开发');

-- 添加课程信息数据
INSERT INTO courses (user_id, course_name, teacher, score, credit, semester) VALUES
(1, '高等数学', '王教授', 92.5, 4.0, '2016-2017学年第一学期'),
(1, '数据结构', '李教授', 88.0, 3.5, '2017-2018学年第一学期'),
(1, '操作系统', '张教授', 90.0, 4.0, '2018-2019学年第二学期'),
(2, '金融学原理', '赵教授', 95.0, 3.0, '2017-2018学年第一学期'),
(2, '投资学', '钱教授', 87.5, 3.0, '2018-2019学年第二学期'),
(3, '电路分析', '孙教授', 85.0, 4.0, '2015-2016学年第二学期'),
(3, '信号与系统', '周教授', 89.0, 3.5, '2016-2017学年第一学期');

-- 添加获奖信息数据
INSERT INTO awards (user_id, award_name, award_level, award_date, issuing_authority, description) VALUES
(1, '全国大学生数学建模竞赛', '国家级二等奖', '2018-10-15', '中国工业与应用数学学会', '团队合作完成的数学建模项目'),
(1, '校级优秀学生干部', '校级', '2019-05-20', '北京大学学生工作处', '担任班级学习委员，组织多次学习活动'),
(2, '全国大学生英语竞赛', '省级一等奖', '2019-04-10', '上海市教育委员会', '在英语写作和口语方面表现突出'),
(3, '电子设计大赛', '国家级三等奖', '2017-11-05', '教育部高等教育司', '设计并实现了一个智能家居控制系统');
awards

ALTER TABLE awards ADD COLUMN issuing_authority VARCHAR(100) AFTER award_date;

