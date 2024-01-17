<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"></jsp:include>
<!-- Body -->
<div class="container-md" style="width: 500px;">
		<div class="mb-3">
			<label for="title" class="form-label">제목 (조회수 : ${bvo.readCount}) [${bvo.cmtQty}]</label> <input
				type="text" name="title" class="form-control" id="title" value="${bvo.title}" disabled
				placeholder="title">
		</div>
		<div class="mb-3">
			<label for="writer" class="form-label">writer</label> <input
				type="text" class="form-control" name="writer" id="writer" value="${bvo.writer}" disabled>
		</div>
		<div class="mb-3">
			<label for="content" class="form-label">content</label>
			<textarea class="form-control" name="content" id="content" rows="3" disabled>${bvo.content}</textarea>
		</div>
		
	<!-- 	<div class="mb-3">
			<label for="content" class="form-label">file</label>
			<input type="file" class="form-control" id="files" name="files" multiple style="display : none;"><br>
			<button type="button" id="trigger" class="btn btn-primary">FileUpload</button>
		</div> -->
		
		<div class="mb-3" id="fileZone">
		
		</div>
		
		
		<button type="submit" class="btn btn-primary" id="regBtn">수정</button>
</div>



<!-- /Body -->
<jsp:include page="../layout/footer.jsp"></jsp:include>