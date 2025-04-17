<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生管理系统 - 添加学历信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dashboard.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>学生管理系统</h1>
            <ul class="nav-menu">
                <li><a href="${pageContext.request.contextPath}/home">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/student/info">学历信息</a></li>
                <li><a href="${pageContext.request.contextPath}/course/info">课程信息</a></li>
                <li><a href="${pageContext.request.contextPath}/award/list">获奖情况</a></li>
                <li><a href="${pageContext.request.contextPath}/user/info">个人信息</a></li>
                <li><a href="${pageContext.request.contextPath}/user/password">修改密码</a></li>
                <li><a href="${pageContext.request.contextPath}/user/logout">退出登录</a></li>
            </ul>
        </div>
        
        <div class="section">
            <h2 class="section-title">添加学历信息</h2>
            
            <c:if test="${not empty error}">
                <div style="color: red; margin-bottom: 15px;">${error}</div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/student/add" method="post">
                <div class="form-group">
                    <label for="school">学校：</label>
                    <input type="text" id="school" name="school" required>
                </div>
                <div class="form-group">
                    <label for="major">专业：</label>
                    <input type="text" id="major" name="major" required>
                </div>
                <div class="form-group">
                    <label for="degree">学位：</label>
                    <select id="degree" name="degree" required>
                        <option value="">请选择</option>
                        <option value="专科">专科</option>
                        <option value="本科">本科</option>
                        <option value="硕士">硕士</option>
                        <option value="博士">博士</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="startDateStr">开始日期：</label>
                    <input type="date" id="startDateStr" name="startDateStr" required>
                </div>
                <div class="form-group">
                    <label for="endDateStr">结束日期：</label>
                    <input type="date" id="endDateStr" name="endDateStr">
                </div>
                <div class="form-group">
                    <label for="description">描述：</label>
                    <textarea id="description" name="description" rows="4"></textarea>
                </div>
                <button type="submit" class="btn">添加</button>
                <a href="${pageContext.request.contextPath}/student/info" class="btn" style="background-color: #ccc;">取消</a>
            </form>
        </div>
        
        <div class="footer">
            © 2023 学生管理系统
        </div>
    </div>
</body>
</html>