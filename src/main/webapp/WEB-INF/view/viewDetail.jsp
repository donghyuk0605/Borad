<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- 공통 JavaScript -->
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://malsup.github.io/jquery.form.js"></script>
<script type="text/javascript" src="../resources/js/viewDetail.js"></script>
<!-- CSS only -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#content {
	height: 100px;
	width: 100px;
	border: 1px solid blue;
}
</style>
</head>
<body>
	<h2 align="center">글 내 용</h2>
	<table class="table">
		<tr>
			<th>[글번호]:</th>
			<td>${ BBSDETILList.seqno}</td>
		</tr>
		<br>
		<tr>
			<th>[글제목]:</th>
			<td>${ BBSDETILList.title}</td>
		</tr>
		<br>
		<tr>
			<th>[작성자]:</th>
			<td>${ BBSDETILList.id}</td>
		</tr>
		,
		<tr>
			<th>[작성일]:</th>
			<td>${ BBSDETILList.register_date}</td>
		</tr>
		<br>
		<tr>
			<th>[첨부파일]</th>
			<td>${ BBSDETILList.file_url}</td>
		</tr>
		<tr>
			<td>${ BBSDETILList.content}</td>
		</tr>
	</table>
	<input type="button" id="del" value="삭제" class="btn btn-secondary">
	<input type="button" id="update" value="수정" class="btn btn-secondary">
	<input type="button" id="relpy" value="답글" class="btn btn-secondary">
	<input type="button" value="돌아가기" onclick="location.href='../Board/bbsListView.do'" class="btn btn-secondary">

	<form name="frm" id="frm" method="post" action="../Board/writeForm.do">
		<input type="hidden" name="seqno" id="seqno" value="${BBSDETILList.seqno}" /> 
		<input type="hidden" name="parent_id" id="parent_id" value="${BBSDETILList.seqno}" />
		<input type="hidden" name="group_id" id="group_id" value="${BBSDETILList.group_id}" />
		<input type="hidden" id="order_no"  value="${BBSDETILList.order_no}" />
	</form>
	</div>
	<div>
		<table class="table">
			<c:forEach items="${COMMENTSLIST}" var="cm">
				<tr>
					<td>댓글작성자:${cm.name}</td>
					<td>댓글내용:${cm.comments_text}</td>
					<td>댓글수정일:${cm.regdate}</td>
					<td><input type="button" id="cmdel" value="댓글 삭제" name="${cm.comments_no}" class="btn btn-secondary cmdel"></td>
				</tr>
				<br>
			</c:forEach>
		</table>
	</div>
	<form id="Commentsform" class="form-inline">
		<div class="form-group">
			<input type="text" name="name" id="Commentsname" placeholder="작성자 " class="form-control mb-2 mr-sm-2"> <br>
			<textarea name="Comments_text" rows="1" cols="70" id="Comments_text" placeholder="댓글을 작성하세요" class="form-control"></textarea>
			<br> <input type="hidden" name="board_no" value="${BBSDETILList.seqno}" /> <input type="button" id="Comments" value="댓글 등록"
				class="btn btn-secondary">
	</form>
</body>
</html>