<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生管理系统 - 登录</title>
    <!-- 引用外部CSS文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css">
</head>
<body>
    <div class="container">
        <h1>学生管理系统</h1>
        <form action="${pageContext.request.contextPath}/user/login" method="post">
            <div class="form-group">
                <label for="username">用户名：</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">密码：</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn">登录</button>
            <div class="links">
                <a href="${pageContext.request.contextPath}/user/forgot">忘记密码？</a>
                <a href="${pageContext.request.contextPath}/user/register">去注册</a>
            </div>
        </form>
        <div class="footer">
            © 2023 学生管理系统
        </div>
    </div>
</body>
</html>