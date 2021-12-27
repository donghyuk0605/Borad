/**
 * 
 */

$(document).ready(function() {
	/** 게시판 - 작성 */
	$('#update').click(function() {
		var boardSubject = $("#bbstitle").val();
		var boardid = $("#bbsid").val();
		var boardContent = $("#bbscontent").val();

		if (boardSubject == "") {
			alert("제목을 입력해주세요.");
			$("#board_subject").focus();
			return;
		}
		if (boardid == "") {
			alert("작성자를 입력해주세요.");
			$("#bbsid").focus();
			return;
		}
		if (boardContent == "") {
			alert("내용을 입력해주세요.");
			$("#board_content").focus();
			return;
		}

		var yn = confirm("게시글을 수정하시겠습니까?");
		if (yn) {

			var filesChk = $("input[name='file']").val();
			if (filesChk == "") {
				$("input[name='file']").remove();
			}

			$("#bbsform").ajaxForm({

				url : "../Board/update.do",
				enctype : "multipart/form-data",
				cache : false,
				async : true,
				type : "POST",
				success : function(obj) {
					if (obj != null) {

						var result = obj;

						if (result == "SUCCESS") {
							alert("게시글 수정을 성공하였습니다.");
							location.href = "../Board/bbsListView.do";
						} else {
							alert("게시글 수정을 실패하였습니다.");
							return;
						}
					}
				},
				error : function(xhr, status, error) {
				}

			}).submit();
		}
	});

});
