<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生管理系统 - 个人信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dashboard.css">
    <style>
        .avatar-container {
            text-align: center;
            margin-bottom: 20px;
        }
        .avatar {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            border: 3px solid #4CAF50;
        }
        .avatar-upload {
            margin-top: 10px;
        }
        #upload-form {
            display: none;
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
            <h2 class="section-title">个人信息</h2>
            
            <div class="avatar-container">
                <img src="${empty user.avatar ? pageContext.request.contextPath.concat('/resources/images/default-avatar.png') : pageContext.request.contextPath.concat(user.avatar)}" alt="用户头像" class="avatar" id="avatar-preview">
                <div class="avatar-upload">
                    <button type="button" class="btn" onclick="document.getElementById('upload-form').style.display='block'">更换头像</button>
                    <div id="upload-form">
                        <form id="avatar-form" enctype="multipart/form-data">
                            <input type="file" name="file" id="avatar-file" accept="image/*">
                            <button type="button" class="btn" onclick="uploadAvatar()">上传</button>
                        </form>
                        <div id="upload-message"></div>
                    </div>
                </div>
            </div>
            
            <c:if test="${not empty message}">
                <div style="color: green; margin-bottom: 15px;">${message}</div>
            </c:if>
            
            <c:if test="${not empty error}">
                <div style="color: red; margin-bottom: 15px;">${error}</div>
            </c:if>
            
            <form action="${pageContext.request.contextPath}/user/update" method="post">
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input type="text" id="username" name="username" class="form-control" value="${user.username}" readonly>
                </div>
                
                <div class="form-group">
                    <label for="realName">姓名</label>
                    <input type="text" id="realName" name="realName" class="form-control" value="${user.realName}">
                </div>
                
                <div class="form-group">
                    <label for="email">邮箱</label>
                    <input type="email" id="email" name="email" class="form-control" value="${user.email}">
                </div>
                
                <div class="form-group">
                    <label for="phone">电话</label>
                    <input type="text" id="phone" name="phone" class="form-control" value="${user.phone}">
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn">保存</button>
                </div>
            </form>
        </div>
        
        <div class="footer">
            © 2023 学生管理系统
        </div>
    </div>
    
    <script>
        function uploadAvatar() {
            var fileInput = document.getElementById('avatar-file');
            var file = fileInput.files[0];
            
            if (!file) {
                document.getElementById('upload-message').innerHTML = '<span style="color: red;">请选择文件</span>';
                return;
            }
            
            var formData = new FormData();
            formData.append('file', file);
            
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '${pageContext.request.contextPath}/file/upload', true);
            
            xhr.onload = function() {
                if (xhr.status === 200) {
                    var response = JSON.parse(xhr.responseText);
                    if (response.success) {
                        document.getElementById('upload-message').innerHTML = '<span style="color: green;">' + response.message + '</span>';
                        document.getElementById('avatar-preview').src = '${pageContext.request.contextPath}' + response.avatarPath;
                        setTimeout(function() {
                            document.getElementById('upload-form').style.display = 'none';
                        }, 2000);
                    } else {
                        document.getElementById('upload-message').innerHTML = '<span style="color: red;">' + response.message + '</span>';
                    }
                } else {
                    document.getElementById('upload-message').innerHTML = '<span style="color: red;">上传失败</span>';
                }
            };
            
            xhr.send(formData);
        }
    </script>
</body>
</html>
