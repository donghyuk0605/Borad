<%-- <%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 공통 JavaScript -->
<script type="text/javascript" src="../resources/js/viewDetail.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<!-- CSS only -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<!-- JS, Popper.js, and jQuery -->

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
	integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

</head>
<body>
	<form id="bbsform" name="bbsform" action="../Board/wirte.do">
		제목:<input type="text" name="title" id="bbstitle" value="${title}"> 
		작성자:<input type="text" name="id" id="bbsid" value="${ BBSDETILList.id}">
		<textarea rows="5" cols="80" name="content" id="bbscontent">${ BBSDETILList.content}
</textarea>
		<input type="hidden" value="${BBSDETILList.seqno}" name="seqno">
		<c:if test="${ ! empty writing.group_id }">
			<input type="hidden" name="parent_id" value="${writing.group_id}" />
		</c:if>
		<c:if test="${ ! empty writing.parent_id }">
			<input type="hidden" name="group_id" value="${writing.parent_id }" />
		</c:if>
		<div align="center">
			<button type="submit">등록</button>
			<input button="reset" value="취소"> <input type="button" value="돌아가기" onclick="location.href='../Board/bbsListView.do'">
		</div>
	</form>
</body>
</html> --%> --%>