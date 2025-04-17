<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生管理系统 - 课程信息</title>
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
            <h2 class="section-title">课程信息列表</h2>
            
            <div class="action-bar">
                <a href="${pageContext.request.contextPath}/course/add" class="btn">添加课程</a>
            </div>
            
            <c:if test="${not empty error}">
                <div style="color: red; margin-bottom: 15px;">${error}</div>
            </c:if>
            
            <c:if test="${empty courses}">
                <p>暂无课程信息，请点击"添加课程"按钮添加。</p>
            </c:if>
            
            <c:if test="${not empty courses}">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>课程名称</th>
                            <th>课程代码</th>
                            <th>教师</th>
                            <th>学分</th>
                            <th>学期</th>
                            <th>成绩</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${courses}" var="course">
                            <tr>
                                <td>${course.courseName}</td>
                                <td>${course.courseCode}</td>
                                <td>${course.teacher}</td>
                                <td>${course.credit}</td>
                                <td>${course.semester}</td>
                                <td>${course.grade}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/course/edit/${course.id}" class="btn-small">编辑</a>
                                    <a href="javascript:void(0);" onclick="if(confirm('确定要删除吗？')) window.location.href='${pageContext.request.contextPath}/course/delete/${course.id}'" class="btn-small btn-danger">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
        
        <div class="footer">
            © 2023 学生管理系统
        </div>
    </div>
</body>
</html>