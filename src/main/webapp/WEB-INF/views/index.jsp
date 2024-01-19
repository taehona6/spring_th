<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="layout/header.jsp"></jsp:include>

<h1>
	${ip}   
</h1>

<P> ${serverTime}. </P>

<a href="/board/register">레지스터 ㄱㄱ</a><br>
<a href="/board/list">리스트</a><br>


<jsp:include page="layout/footer.jsp"></jsp:include>
