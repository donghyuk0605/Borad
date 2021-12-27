<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 공통 JavaScript -->
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://malsup.github.io/jquery.form.js"></script>
<script type="text/javascript" src="../resources/js/bbsListView.js"></script>
<!-- CSS only -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
	<jsp:include page="./header.jsp" />
	<c:if test="${empty LIST }">
등록된 게시글이 존재하지 않습니다.
	<form action="../Board/writeForm.do">
			<input type="submit" value="글쓰기">
		</form>
	</c:if>
	<c:if test="${ ! empty LIST }">
		<table width="100%">
			<tr>
				<td align="right"><b>${STARTROW}~${ENDROW}/${COUNT}</b></td>
			</tr>
		</table>
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>글 번호</th>
					<th>글제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<c:forEach var="w" items="${LIST }">
				<tr>
					<td>${w.seqno }</td>
					<td><pre><a href="../Board/viewDetail.do?SEQNO=${w.seqno }">${w.title }</a></pre></td>
					<td>${w.id }</td>
					<td>${w.register_date }</td>
				</tr>
			</c:forEach>
		</table>
		</form>
		<form action="../Board/writeForm.do">
			<input type="submit" class="btn btn-secondary" value="글쓰기">
		</form>
		<form id="frm" name="frm" method="post">
			<input type="hidden" name="SEQNO" /> <input type="hidden" name="PAGE_NUM" vale="2" />
		</form>
		<c:set var="startPage" value="${currentPage-(currentPage%10 == 0 ? 10:(currentPage%10))+1}" />
		<c:set var="endPage" value="${startPage + 9 }" />
		<c:if test="${endPage > pageCount }">
			<c:set var="endPage" value="${pageCount}" />
		</c:if>
		<c:if test="${startPage > 10 }">
			<a href="../Board/bbsListView.do?PAGE_NUM=${startPage - 1 }">[이전]</a>
		</c:if>

		<c:forEach var="pageNo" begin="${startPage }" end="${endPage }">
			<ul class="pagination">
				<c:if test="${currentPage == pageNo }">
					<font size="5">
				</c:if>
				<li class="page-item"><a href="../Board/bbsListView.do?PAGE_NUM=${pageNo }" class="page-link">${pageNo }</a></li>
				</nav>
				<c:if test="${currentPage == pageNo }">
					</font>
				</c:if>
		</c:forEach>
		</ul>
		<c:if test="${endPage < pageCount }">
			<a href="../Board/bbsListView.do?PAGE_NUM=${endPage + 1 }">[다음]</a>
		</c:if>
	</c:if>
</body>
</html>


























