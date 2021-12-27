package com.spring.board.model;

public class CommentsM {

	private Integer comments_no;// 댓글번호
	private Integer board_no;// 글번호
	private String comments_text;// 댓글내용
	private String name;// 작성자
	private String regdate;// 댓글성일

	public Integer getComments_no() {
		return comments_no;
	}

	public void setComments_no(Integer comments_no) {
		this.comments_no = comments_no;
	}

	public Integer getBoard_no() {
		return board_no;
	}

	public void setBoard_no(Integer board_no) {
		this.board_no = board_no;
	}

	public String getComments_text() {
		return comments_text;
	}

	public void setComments_text(String comments_text) {
		this.comments_text = comments_text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

}
