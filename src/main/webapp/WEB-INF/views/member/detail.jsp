<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"></jsp:include>

<!-- Body -->
<div class="container-md" style="width: 500px;">
	
		<div class="mb-3">
			<label for="email" class="form-label">email</label> 
			<input type="email" name="email" class="form-control" id="email" disabled value="${mvo.email }">
		</div>
		<div class="mb-3">
			<label for="pwd" class="form-label">pwd</label> <input
				type="password" class="form-control" name="pwd" id="pwd" disabled >
		</div>
		<div class="mb-3">
			<label for="nickName" class="form-label">nick_name</label>
			<input type="text" class="form-control" name="nickName" id="nickName" disabled value="${mvo.nickName }">
		</div>
		
		<button type="button" onclick="location.href='/member/modify';" class="btn btn-primary" id="regBtn">수정페이지ㄱ</button>
	
</div>



<!-- /Body -->

<jsp:include page="../layout/footer.jsp"></jsp:include>