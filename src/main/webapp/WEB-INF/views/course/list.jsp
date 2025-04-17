<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" />

<div class="card">
    <div class="card-header d-flex justify-content-between align-items-center">
        <h4 class="mb-0">课程信息</h4>
        <a href="${pageContext.request.contextPath}/course/add" class="btn btn-primary">添加课程</a>
    </div>
    <div class="card-body">
        <c:choose>
            <c:when test="${empty courses}">
                <div class="alert alert-info">暂无课程信息，请点击"添加课程"按钮添加。</div>
            </c:when>
            <c:otherwise>
                <div class="table-responsive">
                    <table class="table table-bordered table-hover">
                        <thead class="thead-light">
                            <tr>
                                <th>课程名称</th>
                                <th>教师</th>
                                <th>学期</th>
                                <th>成绩</th>
                                <th>学分</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="course" items="${courses}">
                                <tr>
                                    <td>${course.courseName}</td>
                                    <td>${course.teacher}</td>
                                    <td>${course.semester}</td>
                                    <td>${course.score}</td>
                                    <td>${course.credit}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/course/edit/${course.id}" class="btn btn-sm btn-info">编辑</a>
                                        <a href="javascript:void(0);" onclick="confirmDelete(${course.id})" class="btn btn-sm btn-danger">删除</a>
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
        if (confirm('确定要删除这条课程信息吗？')) {
            window.location.href = '${pageContext.request.contextPath}/course/delete/' + id;
        }
    }
</script>

<jsp:include page="../common/footer.jsp" />