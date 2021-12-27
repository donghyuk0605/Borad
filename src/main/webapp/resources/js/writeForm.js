/**
 * 
 */

$(document).ready(function() {
	$('#back').click(function() {
		location.href = "../Board/bbsListView.do";
	});

	/** 게시판 - 작성 */
	$('#insertBoard').click(function() {

		var boardSubject = $("#bbstitle").val();
		var boardid = $("#bbsid").val();
		var boardContent = $("#bbscontent").val();
		
	
		if (boardSubject == "") {
			alert("제목을 입력해주세요.");
			$("#bbstitle").focus();
			return;
		}
		if (boardid == "") {
			alert("작성자를 입력해주세요.");
			$("#bbsid").focus();
			return;
		}

		if (boardContent == "") {
			alert("내용을 입력해주세요.");
			$("#bbscontent").focus();
			return;
		}

		var yn = confirm("게시글을 등록하시겠습니까?");
		if (yn) {

			var filesChk = $("input[name='file']").val();
			if (filesChk == "") {
				$("input[name='file']").remove();
			}

			$("#bbsform").ajaxForm({

				url : "../Board/wirte.do",
				enctype : "multipart/form-data",
				cache : false,
				async : true,
				type : "POST",
				success : function(obj) {
					if (obj != null) {

						var result = obj;

						if (result == "SUCCESS") {
							alert("게시글 등록을 성공하였습니다.");
							location.href = '../Board/bbsListView.do';
						} else {
							alert("게시글 등록을 실패하였습니다.");
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
