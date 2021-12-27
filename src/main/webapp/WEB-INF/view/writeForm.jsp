<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 공통 JavaScript -->
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://malsup.github.io/jquery.form.js"></script>
<script type="text/javascript" src="../resources/js/writeForm.js"></script>
<!-- CSS only -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
	<form id="bbsform" name="bbsM" method="post" enctype="multipart/form-data" class="form-group">
		제목:<input type="text" name="title" id="bbstitle" class="form-control" value="${title}"> 
		작성자:<input type="text" name="id" id="bbsid" class="form-control" value="${ bbsm.id}">
		<div class="form-group">
			<label for="bbsfile">파일을 등록하세요.</label> 
			<input type="file" name="file" id="bbsfile" class="form-control-file">
		</div>
		<textarea rows="5" cols="80" name="content" class="form-control" id="bbscontent">${bbsm.content}
		</textarea>
		<input type="hidden" name="order_no" value="${bbsm.order_no + 1 }" />
		<c:if test="${ ! empty bbsm.group_id }">
			<input type="hidden" name="group_id" value="${bbsm.group_id }" />
		</c:if>
		<c:if test="${ ! empty bbsm.parent_id }">
			<input type="hidden" name="parent_id" value="${parId}" />
		</c:if>
		<div align="center">
			<input type="button" class="btn btn-secondary" id="insertBoard" value="등록"> 
			<input type="reset"	class="btn btn-secondary" value="취소"> 
			<input type="button" value="돌아가기" class="btn btn-secondary" id="back">
		</div>
	</form>



</body>
</html>