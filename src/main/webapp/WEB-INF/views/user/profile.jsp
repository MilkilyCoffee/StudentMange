<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../common/header.jsp" />

<div class="card">
    <div class="card-header d-flex justify-content-between align-items-center">
        <h4 class="mb-0">个人信息</h4>
        <a href="${pageContext.request.contextPath}/user/edit" class="btn btn-primary">编辑信息</a>
    </div>
    <div class="card-body">
        <div class="row">
            <div class="col-md-6">
                <table class="table table-bordered">
                    <tr>
                        <th width="30%">用户名</th>
                        <td>${user.username}</td>
                    </tr>
                    <tr>
                        <th>真实姓名</th>
                        <td>${user.realName}</td>
                    </tr>
                    <tr>
                        <th>性别</th>
                        <td>${user.gender}</td>
                    </tr>
                    <tr>
                        <th>出生日期</th>
                        <td>
                            <c:if test="${not empty user.birthDate}">
                                <fmt:formatDate value="${user.birthDate}" pattern="yyyy-MM-dd" />
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th>手机号</th>
                        <td>${user.phone}</td>
                    </tr>
                    <tr>
                        <th>邮箱</th>
                        <td>${user.email}</td>
                    </tr>
                    <tr>
                        <th>地址</th>
                        <td>${user.address}</td>
                    </tr>
                    <tr>
                        <th>注册时间</th>
                        <td>
                            <fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />