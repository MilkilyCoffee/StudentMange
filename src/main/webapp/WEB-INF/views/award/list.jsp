<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生管理系统 - 获奖情况</title>
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
            <h2 class="section-title">获奖情况列表</h2>
            
            <div class="action-bar">
                <a href="${pageContext.request.contextPath}/award/add" class="btn">添加获奖信息</a>
            </div>
            
            <c:if test="${not empty error}">
                <div style="color: red; margin-bottom: 15px;">${error}</div>
            </c:if>
            
            <c:if test="${empty awards}">
                <p>暂无获奖信息，请点击"添加获奖信息"按钮添加。</p>
            </c:if>
            
            <c:if test="${not empty awards}">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>奖项名称</th>
                            <th>奖项级别</th>
                            <th>颁发机构</th>
                            <th>获奖日期</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${awards}" var="award">
                            <tr>
                                <td>${award.awardName}</td>
                                <td>${award.awardLevel}</td>
                                <td>${award.issuer}</td>
                                <td>
                                    <fmt:formatDate value="${award.awardDate}" pattern="yyyy-MM-dd"/>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/award/edit/${award.id}" class="btn-small">编辑</a>
                                    <a href="javascript:void(0);" onclick="if(confirm('确定要删除吗？')) window.location.href='${pageContext.request.contextPath}/award/delete/${award.id}'" class="btn-small btn-danger">删除</a>
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