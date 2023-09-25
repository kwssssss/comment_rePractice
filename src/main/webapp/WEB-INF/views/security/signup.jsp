<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ include file="../layouts/header.jsp"%>
<%-- 개별 페이지 --%>

<div style="width: 500px" class="mx-auto">

	<h1 class="my-5">
		<i class="fa-solid fa-user-plus"></i> 회원가입
	</h1>
	<div>
		<form:form modelAttribute="member" action="/security/signup?_csrf=${_csrf.token}" enctype="multipart/form-data">
			<div class="panel panel-default">
				<div class="panel-body">
					<form role="form" method="post">
						<div class="form-group">
							<form:label path="username">
								<i class="fa-solid fa-user"></i> ID : </form:label>
							<form:input path="username" cssclass="form-control" />
							<form:errors path="username" cssclass="errors" />
						</div>
						<div class="form-group">
							<form:label path="password">
								<i class="fa-solid fa-lock"></i> PW : </form:label>
							<form:input path="password" cssclass="form-control" />
							<form:errors path="password" cssclass="errors" />
						</div>
						<div class="form-group">
							<form:label path="password2">
								<i class="fa-solid fa-lock"></i> PW 재확인 : </form:label>
							<form:input path="password2" cssclass="form-control" />
							<form:errors path="password2" cssclass="errors" />
						</div>
						<div class="form-group">
							<form:label path="email">
								<i class="fa-regular fa-envelope"></i> Email : </form:label>
							<form:input path="email" cssclass="form-control" />
							<form:errors path="email" cssclass="errors" />
						</div>

						
								<input type="file" name="avatar"/>
								<!-- multiple 추가하면 여러개 파일 선택 가능 -->

						<div class="text-center">
							<button type="submit" class="btn btn-primary">
								<i class="fas fa-check"></i>회원가입
							</button>
						</div>

					</form>
				</div>
			</div>




		</form:form>

	</div>
</div>

<%@ include file="../layouts/footer.jsp"%>