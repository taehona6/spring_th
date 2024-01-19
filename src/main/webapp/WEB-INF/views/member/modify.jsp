<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"></jsp:include>

<!-- Body -->
<div class="container-md" style="width: 500px;">
	<form action="/member/modify" method="post">
		<div class="mb-3">
			<label for="email" class="form-label">email</label> 
			<input type="email" name="email" class="form-control" id="email" readonly value="${mvo.email}">
		</div>
		<div class="mb-3">
			<label for="pwd" class="form-label">pwd</label> <input
				type="password" class="form-control" name="pwd" id="pwd">
		</div>
		<div class="mb-3">
			<label for="nickName" class="form-label">nick_name</label>
			<input type="text" class="form-control" name="nickName" id="nickName" value="${mvo.nickName}">
		</div>
		
		<button type="submit" class="btn btn-primary" id="regBtn">수정하기</button>
	</form>
</div>



<!-- /Body -->

<jsp:include page="../layout/footer.jsp"></jsp:include>