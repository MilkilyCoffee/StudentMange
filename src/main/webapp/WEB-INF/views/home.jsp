<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生管理系统 - 首页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dashboard.css">
    <style>
        .welcome-container {
            display: flex;
            align-items: center;
            margin-bottom: 30px;
        }
        .avatar {
            width: 100px;
            height: 100px;
            border-radius: 50%;
            object-fit: cover;
            border: 3px solid #4CAF50;
            margin-right: 20px;
        }
        .welcome-text {
            flex: 1;
        }
    </style>
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
            <div class="welcome-container">
                <img src="${empty user.avatar ? pageContext.request.contextPath.concat('/resources/images/default-avatar.png') : pageContext.request.contextPath.concat(user.avatar)}" alt="用户头像" class="avatar">
                <div class="welcome-text">
                    <h2>欢迎，${user.realName != null ? user.realName : user.username}！</h2>
                    <p>这是您的学生管理系统首页。您可以通过上方导航栏访问各个功能模块。</p>
                </div>
            </div>
            
            <div class="dashboard-summary">
                <h3>系统功能概览</h3>
                <ul>
                    <li><strong>学历信息：</strong>管理您的学历背景信息</li>
                    <li><strong>课程信息：</strong>管理您的课程记录</li>
                    <li><strong>获奖情况：</strong>记录您获得的奖项和荣誉</li>
                    <li><strong>个人信息：</strong>更新您的个人资料</li>
                    <li><strong>修改密码：</strong>更改您的登录密码</li>
                </ul>
            </div>
        </div>
        
        <div class="footer">
            © 2023 学生管理系统
        </div>
    </div>
</body>
</html>