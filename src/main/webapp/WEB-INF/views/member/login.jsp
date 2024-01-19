<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"></jsp:include>
<!-- Body -->

<div class="container-md" style="width: 500px;">
	<form action="/member/login" method="post">
		<div class="mb-3">
			<label for="email" class="form-label">email</label> 
			<input type="text" name="email" class="form-control" id="email"	placeholder="title">
		</div>
		<div class="mb-3">
			<label for="pwd" class="form-label">pwd</label>
			<input type="password" class="form-control" name="pwd" id="pwd">
		</div>
		<c:if test="${not empty param.errMsg}">
			<div class ="mb-3 text-danger">
				<c:choose>
					<c:when test="${param.errMsg eq 'Bad credentials'}">
						<c:set value="이메일&비밀번호가 일치하지 않습니다." var="errText"></c:set>
					</c:when>
					<c:otherwise>
						<c:set value="관리자에게 문의해주세요" var="errText"></c:set>
					</c:otherwise>
				</c:choose>
				${errText}
			</div>
		</c:if>
		
		<div class="mb-3">
			<button type="submit" class="btn btn-dark">Login</button>
		</div>
	</form>
</div>




<!-- /Body -->
<jsp:include page="../layout/footer.jsp"></jsp:include>