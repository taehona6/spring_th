<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인프로젝트</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar bg-primary navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">Spring</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/">Home</a>
        </li>
        <sec:authorize access="isAuthenticated()">
	        <sec:authentication property="principal.mvo.email" var="authEmail"/>
	        <sec:authentication property="principal.mvo.nickName" var="authNick"/>
	        <sec:authentication property="principal.mvo.authList" var="auths"/>
	        
	        <li class="nav-item">
				<a class="nav-link" href="/board/register">글쓰기</a>
	        </li>
        	<li class="nav-item">
				<a class="nav-link" href="/board/list">게시판</a>
			</li>
	        
	        <c:choose>
	        	<c:when test="${
	        					auths
	        					.stream()
	        					.anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN'))
	        					.get()
	        					}">
	        		<li class="nav-item">
	          			<a class="nav-link" href="/member/list">회원리스트${authNick}(${authEmail}/ADMIN)</a>
		        	</li>
	        	</c:when>
	        	<c:otherwise>
	        		<li class="nav-item">
	          			<a class="nav-link" href="/member/detail">${authNick}(${authEmail})</a>
		        	</li>
	        	</c:otherwise>
	        </c:choose>
	        
			<li class="nav-item">
	         	<a class="nav-link" href="/member/logout" id="logoutLink">로그아웃</a>	
		    </li>
	         	<form action="/member/logout" method="post" id="logoutForm">
	         			<!-- 인증된(로그인 한) 이메일 -->
	         		<input type="hidden" name="email" value="${authEmail}">
	         	</form>
		</sec:authorize>
	
        <sec:authorize access="isAnonymous()">
	    	<li class="nav-item">
	    		<a class="nav-link" href="/member/register">회원가입</a>
	    	</li>
	 		<li class="nav-item">
	   			<a class="nav-link" href="/member/login">로그인</a>
			</li>
	    </sec:authorize>
      </ul>
    </div>
  </div>
</nav>

<script>

document.getElementById('logoutLink').addEventListener('click',(e)=>{
    e.preventDefault();
    document.getElementById('logoutForm').submit();
})
</script>
