/**
 * 
 */

/** 게시판 - 삭제 */
$(document).ready(function() {

	$('#del').click(function() {
		var boardSeq = $('#group_id').val();
		var boardon = $('#order_no').val();
		
		var yn = confirm("게시글을 삭제하시겠습니까?");
		if (yn) {

			$.ajax({

				url : "../Board/delete.do",
				data : {
					"Id" : boardSeq,
					"o_no":boardon	
				},
				dataType : "text",
				cache : false,
				async : true,
				type : "POST",
				success : function(obj) {
					if (obj != null) {

						if (obj == "SUCCESS") {
							alert("게시글 삭제를 성공하였습니다.");
							location.href = "../Board/bbsListView.do";
						} else {
							alert("게시글 삭제를 실패하였습니다.");
							return;
						}
					}
				}

			});
		}
	});

	/** 댓글 - 삭제 */
	$('.cmdel').click(function() {
		var seq = $(this).attr("name");
		var yn = confirm("리플을 삭제하시겠습니까?");
		if (yn) {

			$.ajax({

				url : "../Comments/delete.do",
				data : {
					"Id" : seq
				},
				dataType : "text",
				cache : false,
				async : true,
				type : "POST",
				success : function(obj) {
					if (obj != null) {

						if (obj == "SUCCESS") {
							alert("리플 삭제를 성공하였습니다.");
							location.reload(true);

						} else {
							alert("리플 삭제를 실패하였습니다.");
							return;
						}
					}
				}

			});
		}
	});

	/** 댓글 - 작성 */
	$('#Comments').click(function() {

		var Commentsname = $("#Commentsname").val();
		var Commentstext = $("#Commentstext").val();

		if (Commentsname == "") {
			alert("작성자를 입력해주세요.");
			$("#Commentsname").focus();
			return;
		}

		if (Commentstext == "") {
			alert("내용을 입력해주세요.");
			$("#Commentstext").focus();
			return;
		}

		var yn = confirm("게시글을 등록하시겠습니까?");
		if (yn) {

			$.ajax({

				url : "../Comments/Commentswrite.do",
				data : $("#Commentsform").serialize(),
				dataType : "text",
				cache : false,
				async : true,
				type : "POST",
				success : function(obj) {
					if (obj != null) {

						var result = obj;

						if (result == "SUCCESS") {
							alert("게시글 등록을 성공하였습니다.");
							location.reload(true);
						} else {
							alert("게시글 등록을 실패하였습니다.");
							return;
						}
					}
				},
				error : function(xhr, status, error) {
				}

			});
		}
	});

	$('#update').click(function() {

		var boardSeq = $('#seqno').val();

		location.href = '../Board/updateFrom.do?Id=' + boardSeq;

	});
	$('#relpy').click(function() {
		var chk = confirm("답글을 다시겠하시겠습니까?");
		if (chk) {

			$('#frm').submit();

		}
	});
});
