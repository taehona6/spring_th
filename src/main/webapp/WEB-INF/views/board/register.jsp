<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<jsp:include page="../layout/header.jsp"></jsp:include>

<!-- Body -->
<sec:authentication property="principal.mvo.nickName" var="authNick"/>
<div class="container-md" style="width: 500px;">
	<form action="/board/register" method="post" enctype="multipart/form-data">
		<div class="mb-3">
			<label for="title" class="form-label">title</label> <input
				type="text" name="title" class="form-control" id="title"
				placeholder="title">
		</div>
		<div class="mb-3">
			<label for="writer" class="form-label">writer</label> <input
				type="text" class="form-control" name="writer" id="writer" value="${authNick}" readonly>
		</div>
		<div class="mb-3">
			<label for="content" class="form-label">content</label>
			<textarea class="form-control" name="content" id="content" rows="3"></textarea>
		</div>
		
		<div class="mb-3">
			<label for="content" class="form-label">file</label>
			<input type="file" class="form-control" id="files" name="files" multiple style="display : none;"><br>
			<button type="button" id="trigger" class="btn btn-primary">FileUpload</button>
		</div>
		
		<div class="mb-3" id="fileZone">
		
		</div>
		
		
		<button type="submit" class="btn btn-primary" id="regBtn">게시물 작성</button>
	</form>
</div>

<script src="/resources/js/boardRegister.js"></script>

<!-- /Body -->
<jsp:include page="../layout/footer.jsp"></jsp:include>
