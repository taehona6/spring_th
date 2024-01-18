<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"></jsp:include>
<!-- Body -->
<div class="container-md" style="width: 500px;">
	<form action="/board/modify" method="post" enctype="multipart/form-data">
	<c:set value="${bdto.bvo}" var="bvo"/>
		<div class="mb-3">
			<label for="title" class="form-label">[${bvo.bno}]번 게시글 (조회수 : ${bvo.readCount} / [${bvo.cmtQty}])</label> <input
				type="text" name="title" class="form-control" id="title" value="${bvo.title}" 
				placeholder="title">
				<input type="hidden" name="bno" value="${bvo.bno}">
		</div>
		<div class="mb-3">
			<label for="writer" class="form-label">writer</label> <input
				type="text" class="form-control" name="writer" id="writer" value="${bvo.writer}" readonly>
		</div>
		<div class="mb-3">
			<label for="content" class="form-label">content</label>
			<textarea class="form-control" name="content" id="content" rows="3" >${bvo.content}</textarea>
		</div>
		 
		<input type="hidden" id="uuids" name="uuids">
		<div class="mb-3">
			<label for="files" class="form-label">file</label>
			<ul class="list-group">
				<c:forEach items = "${bdto.flist}" var="fvo">
				  <li class="list-group-item">
					<c:choose>
						<c:when test="${fvo.fileType == 1 }">
							<div>
								<img src="/upload/${fvo.saveDir}/${fvo.uuid}_th_${fvo.fileName}">
							</div>
						</c:when>
						<c:otherwise>
							<div>
							</div>
						</c:otherwise>
					</c:choose>
					<div class="ms-2 me-auto">
						<div><span>${fvo.fileName} ${fvo.fileSize}byte</span>
						<button type="button" data-uuid="${fvo.uuid}" class="btn file-x">X</button></div>
					</div>
				  </li>
			  </c:forEach>
			</ul>			
		</div>
		
		<div class="mb-3">
			<label for="content" class="form-label">file</label>
			<input type="file" class="form-control" id="files" name="files" multiple style="display : none;"><br>
			<button type="button" id="trigger" class="btn btn-primary">FileUpload</button>
		</div>
		
		<div class="mb-3" id="fileZone">
		
		</div>
		
		
		<button type="submit" class="btn btn-primary" id="regBtn">수정하기</button>
		</form>
</div>

	

<script src="/resources/js/boardRegister.js"></script>
<script src="/resources/js/boardModify.js"></script>
<!-- /Body -->
<jsp:include page="../layout/footer.jsp"></jsp:include>