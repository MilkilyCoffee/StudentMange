<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生管理系统 - 添加获奖信息</title>
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
            <h2 class="section-title">添加获奖信息</h2>
            
            <c:if test="${not empty error}">
                <div style="color: red; margin-bottom: 15px;">${error}</div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/award/add" method="post">
                <div class="form-group">
                    <label for="awardName">奖项名称</label>
                    <input type="text" id="awardName" name="awardName" class="form-control" required>
                </div>
                
                <div class="form-group">
                    <label for="awardLevel">奖项级别</label>
                    <input type="text" id="awardLevel" name="awardLevel" class="form-control">
                </div>
                
                <div class="form-group">
                    <label for="issuer">颁发机构</label>
                    <input type="text" id="issuer" name="issuer" class="form-control">
                </div>
                
                <div class="form-group">
                    <label for="awardDateStr">获奖日期</label>
                    <input type="date" id="awardDateStr" name="awardDateStr" class="form-control">
                </div>
                
                <div class="form-group">
                    <label for="description">描述</label>
                    <textarea id="description" name="description" class="form-control" rows="4"></textarea>
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn">保存</button>
                    <a href="${pageContext.request.contextPath}/award/list" class="btn" style="margin-left: 10px;">返回</a>
                </div>
            </form>
        </div>
        
        <div class="footer">
            © 2023 学生管理系统
        </div>
    </div>
</body>
</html>