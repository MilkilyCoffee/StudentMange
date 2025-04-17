<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../common/header.jsp" />

<div class="card">
    <div class="card-header d-flex justify-content-between align-items-center">
        <h4 class="mb-0">学历信息</h4>
        <a href="${pageContext.request.contextPath}/education/add" class="btn btn-primary">添加学历</a>
    </div>
    <div class="card-body">
        <c:choose>
            <c:when test="${empty educations}">
                <div class="alert alert-info">暂无学历信息，请点击"添加学历"按钮添加。</div>
            </c:when>
            <c:otherwise>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead class="thead-light">
                            <tr>
                                <th>学校名称</th>
                                <th>教育阶段</th>
                                <th>专业</th>
                                <th>起止时间</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="education" items="${educations}">
                                <tr>
                                    <td>${education.schoolName}</td>
                                    <td>${education.educationLevel}</td>
                                    <td>${education.major}</td>
                                    <td>
                                        <fmt:formatDate value="${education.startDate}" pattern="yyyy-MM-dd" /> 至 
                                        <fmt:formatDate value="${education.endDate}" pattern="yyyy-MM-dd" />
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/education/edit/${education.id}" class="btn btn-sm btn-info">编辑</a>
                                        <a href="javascript:void(0);" onclick="confirmDelete(${education.id})" class="btn btn-sm btn-danger">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<script>
    function confirmDelete(id) {
        if (confirm('确定要删除这条学历信息吗？')) {
            window.location.href = '${pageContext.request.contextPath}/education/delete/' + id;
        }
    }
</script>

<jsp:include page="../common/footer.jsp" />