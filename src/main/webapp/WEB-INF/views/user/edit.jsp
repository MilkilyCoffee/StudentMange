<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../common/header.jsp" />

<div class="card">
    <div class="card-header">
        <h4 class="mb-0">编辑个人信息</h4>
    </div>
    <div class="card-body">
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/user/update" method="post">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">用户名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control-plaintext" value="${user.username}" readonly>
                </div>
            </div>
            
            <div class="form-group row">
                <label for="realName" class="col-sm-2 col-form-label">真实姓名</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="realName" name="realName" value="${user.realName}">
                </div>
            </div>
            
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">性别</label>
                <div class="col-sm-10">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="male" value="男" ${user.gender eq '男' ? 'checked' : ''}>
                        <label class="form-check-label" for="male">男</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="gender" id="female" value="女" ${user.gender eq '女' ? 'checked' : ''}>
                        <label class="form-check-label" for="female">女</label>
                    </div>
                </div>
            </div>
            
            <div class="form-group row">
                <label for="birthDate" class="col-sm-2 col-form-label">出生日期</label>
                <div class="col-sm-10">
                    <input type="date" class="form-control" id="birthDate" name="birthDateStr" 
                           value="<fmt:formatDate value="${user.birthDate}" pattern="yyyy-MM-dd" />">
                </div>
            </div>
            
            <div class="form-group row">
                <label for="phone" class="col-sm-2 col-form-label">手机号</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}">
                </div>
            </div>
            
            <div class="form-group row">
                <label for="email" class="col-sm-2 col-form-label">邮箱</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="email" name="email" value="${user.email}">
                </div>
            </div>
            
            <div class="form-group row">
                <label for="address" class="col-sm-2 col-form-label">地址</label>
                <div class="col-sm-10">
                    <textarea class="form-control" id="address" name="address" rows="3">${user.address}</textarea>
                </div>
            </div>
            
            <div class="form-group row">
                <div class="col-sm-10 offset-sm-2">
                    <button type="submit" class="btn btn-primary">保存</button>
                    <a href="${pageContext.request.contextPath}/user/profile" class="btn btn-secondary">取消</a>
                </div>
            </div>
        </form>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />