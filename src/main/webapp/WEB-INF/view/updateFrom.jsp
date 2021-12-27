<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 공통 JavaScript -->
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://malsup.github.io/jquery.form.js"></script>
<script type="text/javascript" src="../resources/js/updateFrom.js"></script>
<!-- CSS only -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
	<form id="bbsform" name="bbsM" class="form-group" method="post" enctype="multipart/form-data">
		제목:<input type="text" name="title" id="bbstitle" class="form-control" value="${ bbsm.title}"> 
		작성자:<input type="text" name="id" id="bbsid" class="form-control" value="${ bbsm.id}">
		<textarea rows="5" cols="80" name="content" id="bbscontent">${ bbsm.content}
</textarea>
		<input type="file" name="file" id="bbsfile" class="form-control-file"> 
		<input type="hidden" value="${bbsm.seqno}" name="seqno">
		<div align="center">
			<input type="button" value="수정" class="btn btn-secondary" id="update"> 
			<input input type="reset" class="btn btn-secondary" value="취소">
			<input type="button" value="돌아가기" class="btn btn-secondary" onclick="location.href='../Board/bbsListView.do'">
		</div>
	</form>
</body>
</html>