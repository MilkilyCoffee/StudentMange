<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" />

<div class="card">
    <div class="card-header">
        <h4 class="mb-0">添加学历信息</h4>
    </div>
    <div class="card-body">
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/education/add" method="post">
            <div class="form-group row">
                <label for="schoolName" class="col-sm-2 col-form-label">学校名称</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="schoolName" name="schoolName" required>
                </div>
            </div>
            
            <div class="form-group row">
                <label for="educationLevel" class="col-sm-2 col-form-label">教育阶段</label>
                <div class="col-sm-10">
                    <select class="form-control" id="educationLevel" name="educationLevel" required>
                        <option value="">请选择</option>
                        <option value="小学">小学</option>
                        <option value="初中">初中</option>
                        <option value="高中">高中</option>
                        <option value="大专">大专</option>
                        <option value="本科">本科</option>
                        <option value="硕士">硕士</option>
                        <option value="博士">博士</option>
                    </select>
                </div>
            </div>
            
            <div class="form-group row">
                <label for="major" class="col-sm-2 col-form-label">专业</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="major" name="major">
                </div>
            </div>
            
            <div class="form-group row">
                <label for="startDate" class="col-sm-2 col-form-label">开始时间</label>
                <div class="col-sm-10">
                    <input type="date" class="form-control" id="startDate" name="startDateStr" required>
                </div>
            </div>
            
            <div class="form-group row">
                <label for="endDate" class="col-sm-2 col-form-label">结束时间</label>
                <div class="col-sm-10">
                    <input type="date" class="form-control" id="endDate" name="endDateStr">
                </div>
            </div>
            
            <div class="form-group row">
                <label for="description" class="col-sm-2 col-form-label">描述</label>
                <div class="col-sm-10">
                    <textarea class="form-control" id="description" name="description" rows="3"></textarea>
                </div>
            </div>
            
            <div class="form-group row">
                <div class="col-sm-10 offset-sm-2">
                    <button type="submit" class="btn btn-primary">保存</button>
                    <a href="${pageContext.request.contextPath}/education/list" class="btn btn-secondary">取消</a>
                </div>
            </div>
        </form>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />