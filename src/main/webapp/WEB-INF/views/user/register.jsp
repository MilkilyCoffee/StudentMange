<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生管理系统 - 用户注册</title>
    <!-- 引用外部CSS文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
    <!-- 可以在这里添加页面特定的样式 -->
    <style>
        .radio-group {
            display: flex;
            gap: 20px;
            margin-top: 5px;
        }
        
        .radio-label {
            display: flex;
            align-items: center;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>用户注册</h1>
        <form action="${pageContext.request.contextPath}/user/register" method="post">
            <!-- 表单内容保持不变 -->
            <div class="form-group">
                <label for="username">用户名：</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">密码：</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="realName">真实姓名：</label>
                <input type="text" id="realName" name="realName" required>
            </div>
            <div class="form-group">
                <label>性别：</label>
                <div class="radio-group">
                    <label class="radio-label">
                        <input type="radio" name="gender" value="男" checked> 男
                    </label>
                    <label class="radio-label">
                        <input type="radio" name="gender" value="女"> 女
                    </label>
                </div>
            </div>
            <div class="form-group">
                <label for="email">邮箱：</label>
                <input type="email" id="email" name="email">
            </div>
            <div class="form-group">
                <label for="phone">手机号：</label>
                <input type="text" id="phone" name="phone">
            </div>
            <button type="submit" class="btn">注册</button>
            <div class="links">
                <a href="${pageContext.request.contextPath}/user/login">已有账号？去登录</a>
            </div>
        </form>
        <div class="footer">
            © 2023 学生管理系统
        </div>
    </div>
</body>
</html>