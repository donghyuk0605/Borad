<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
</head>
<body>
	<jsp:include page="./header.jsp" />
	<button type="button" class="btn btn-secondary btn-lg btn-block" onclick="location.href='Board/bbsListView.do'">게시판 들어가기</button>
</body>
</html>
