<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"></jsp:include>

<!-- Body -->
<div class="container-md" style="width: 500px;">
	<form class="row g-3" action="/member/register" method="post">
		<div>
		<span>이메일</span>
		</div>
		<div class="col-auto">
			<input type="text" name="email" class="form-control" id="email">
		</div>
		<div class="col-auto">
			<button type="button" id="idValidBtn" class="btn btn-primary mb-3">중복확인</button>
		</div>
		<div class="mb-3">
			<label for="pwd" class="form-label">비밀번호</label> <input
				type="password" class="form-control" name="pwd" id="pwd">
		</div>
		<div class="mb-3">
			<label for="pwd" class="form-label">비밀번호 확인</label> <input
				type="password" class="form-control" id="pwd2">
		</div>
		<div class="mb-3">
			<label for="nickName" class="form-label">닉네임</label>
			<input type="text" class="form-control" name="nickName" id="nickName">
		</div>
		
		<button type="submit" class="btn btn-primary" id="regBtn">가입</button>
	</form>
</div>


<script src="/resources/js/memberRegister.js"></script>
<!-- /Body -->

<jsp:include page="../layout/footer.jsp"></jsp:include>