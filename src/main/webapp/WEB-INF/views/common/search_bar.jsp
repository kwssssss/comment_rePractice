<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<script src="/resources/js/search.js"></script>

<div class="d-flex justify-content-between align-items-center my-4">
	<div>총 ${pageMaker.total} 건 ( ${pageMaker.cri.pageNum} ..
		${pageMaker.totalPage })</div>
	<div>
		<form:form id="searchForm" modelAttribute="cri" method="get" class="d-flex">
			<form:hidden path="pageNum"/>
			<form:hidden path="amount"/> 
			<form:select path="type" items="${searchTypes}" class="form-select rounded-0 ml-1"/>
			
				<%-- <option value="" ${pageMaker.cri.type == null ? 'selected' : ''}>-- 검색대상선택 --</option>
				<option value="R" ${pageMaker.cri.type eq 'R' ? 'selected' : ''}>권역</option>
				<option value="T" ${pageMaker.cri.type eq 'T' ? 'selected' : ''}>제목</option>
				<option value="D" ${pageMaker.cri.type eq 'D' ? 'selected' : ''}>내용</option>
				
				<option value="TD" ${pageMaker.cri.type eq 'TD' ? 'selected' : ''}>제목+내용</option>
				<option value="TR" ${pageMaker.cri.type eq 'TR' ? 'selected' : ''}>권역+제목</option>
				<option value="TRD" ${pageMaker.cri.type eq 'TRD' ? 'selected' : ''}>권역+제목+내용</option> <!--search.jsp형태로 만들어 common에 넣을 수 있음  --> --%>
			
			<div class="input-group">
				<form:input path="keyword" cssClass="form-control rounded-0"/>
				<button type="submit" class="btn btn-success rounded-0">
					<i class="fa-solid fa-magnifying-glass"></i> 검색
				</button>
			</div>
		</form:form>

	</div>
</div>