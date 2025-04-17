<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生管理系统 - 学历信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dashboard.css">
    <style>
        .education-table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        
        .education-table th, .education-table td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        
        .education-table th {
            background-color: #f2f2f2;
        }
        
        .education-table tr:hover {
            background-color: #f5f5f5;
        }
        
        .action-buttons {
            display: flex;
            gap: 5px;
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
            <h2 class="section-title">学历信息列表</h2>
            <a href="${pageContext.request.contextPath}/student/add" class="btn" style="margin-bottom: 15px;">添加学历信息</a>
            
            <c:if test="${empty educations}">
                <p>暂无学历信息</p>
            </c:if>
            
            <c:if test="${not empty educations}">
                <table class="education-table">
                    <thead>
                        <tr>
                            <th>学校</th>
                            <th>专业</th>
                            <th>学位</th>
                            <th>开始日期</th>
                            <th>结束日期</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${educations}" var="education">
                            <tr>
                                <td>${education.school}</td>
                                <td>${education.major}</td>
                                <td>${education.degree}</td>
                                <td><fmt:formatDate value="${education.startDate}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatDate value="${education.endDate}" pattern="yyyy-MM-dd"/></td>
                                <td class="action-buttons">
                                    <a href="${pageContext.request.contextPath}/student/edit/${education.id}" class="btn btn-view">编辑</a>
                                    <a href="javascript:void(0);" onclick="confirmDelete(${education.id})" class="btn" style="background-color: #f44336;">删除</a>
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
    
    <script>
        function confirmDelete(id) {
            if (confirm("确定要删除这条学历信息吗？")) {
                window.location.href = "${pageContext.request.contextPath}/student/delete/" + id;
            }
        }
    </script>
</body>
</html>