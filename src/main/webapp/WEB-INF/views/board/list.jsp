<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<jsp:include page="../layout/header.jsp"></jsp:include>

<!-- Body -->

<div class="container-md" style="width: 1000px;">

	<!-- 검색라인 -->
	<div style="width:50%">
		<form action="/board/list" method="get">
			<div class="input-group mb-3">
				<select name="type" class="form-select" id="search" >
					<option ${ph.pgvo.type == null ? 'selected' : '' }>선택</option>
					<option value="t" ${ph.pgvo.type == 't' ? 'selected' : ''}>제목</option>
					<option value="w" ${ph.pgvo.type == 'w' ? 'selected' : ''}>작성자</option>
					<option value="c" ${ph.pgvo.type == 'c' ? 'selected' : ''}>내용</option>
					<option value="tc"${ph.pgvo.type == 'tc' ? 'selected' : ''}>제목+내용</option>
					<option value="wc"${ph.pgvo.type == 'wc' ? 'selected' : ''}>작성자+내용</option>
					<option value="twc"${ph.pgvo.type == 'twc' ? 'selected' : ''}>전체</option>
				</select>
				<input type="text" name="keyword" placeholder="검색어" class="form-control" value="${ph.pgvo.keyword }">
				<input type="hidden" name="pageNo" value="1">
				<input type="hidden" name="qty" value="10">
				<button class="btn btn-primary">검색</button>
			</div>
		</form>
	</div>

	<table class="table">
		<thead>
			<tr>
				<th scope="col">글번호</th>
				<th scope="col">제목</th>
				<th scope="col">작성자</th>
				<th scope="col">작성일</th>
				<th scope="col">조회수</th>
				<th scope="col">댓글개수</th>
				<th scope="col">파일개수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="bvo">
				<tr>
					<th><a href="/board/${bvo.bno}">${bvo.bno}</a></th>
					<th><a href="/board/${bvo.bno}">${bvo.title}</a></th>
					<td>${bvo.writer}</td>
					<td>${bvo.regAt}</td>
					<td>${bvo.readCount}</td>
					<td>${bvo.cmtQty}</td>
					<td>${bvo.hasFile}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
	<!-- 페이지네이션 -->
	<div style="width:50%;margin:0 auto">
	<nav aria-label="Page navigation example">
	  <ul class="pagination">
	    <c:if test="${ph.prev }">
		    <li class="page-item">
		      <a class="page-link" href="/board/list?pageNo=${ph.startPage-1 }" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
	    </c:if>
	    
	    <c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
		    <li class="page-item ${ph.pgvo.pageNo == i ? 'active' : ''}"><a class="page-link" href="/board/list?pageNo=${i}">${i }</a></li>
	    </c:forEach>
	    
	    <c:if test="${ph.next }">
		    <li class="page-item">
		      <a class="page-link" href="/board/list?pageNo=${ph.endPage+1}" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
	    </c:if>
	
	  </ul>
	</nav>
	</div>
	
	
</div>

<!-- /Body -->
<jsp:include page="../layout/footer.jsp"></jsp:include>
