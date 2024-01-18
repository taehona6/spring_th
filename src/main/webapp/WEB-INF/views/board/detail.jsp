<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../layout/header.jsp"></jsp:include>
<!-- Body -->
<div class="container-md" style="width: 500px;">
	<c:set value="${bdto.bvo}" var="bvo"/>
	
		<div class="mb-3">
			<label for="title" class="form-label">[${bvo.bno}]번 게시글 (조회수 : ${bvo.readCount} / [${bvo.cmtQty}])</label> <input
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
		 
		
		<div class="mb-3" id="fileZone">
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
						<div><a href="/upload/${fvo.saveDir}/${fvo.uuid}_${fvo.fileName}" download>${fvo.fileName}</a>${fvo.fileSize}byte</div>
					</div>
				  </li>
			  </c:forEach>
			</ul>			
		</div>
		
		
		<button type="button" onclick="location.href='/board/${bvo.bno}/modify'" class="btn btn-primary" id="regBtn">수정</button>
		<button type="button" onclick="location.href='/board/${bvo.bno}/delete'" class="btn btn-primary">글삭제</button>

		<!-- 댓글 라인 -->
		  <div class="mb-3 row">
		    <label id="cmtWriter" for="inputPassword" class="col-sm-2 col-form-label">Tester</label>
		    <div class="col-sm-10">
		      <input type="text" class="" id="cmtText"><button type="button" id="cmtPostBnt" class="btn btn-primary">등록</button>
		    </div>
		  </div>

</div>


<script src="/resources/js/boardComment.js"></script>
<!-- /Body -->
<jsp:include page="../layout/footer.jsp"></jsp:include>