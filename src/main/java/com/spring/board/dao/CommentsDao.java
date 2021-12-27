package com.spring.board.dao;

import java.util.List;

import com.spring.board.model.CommentsM;


public interface CommentsDao {
	public void Commentsinset(CommentsM Commentsm);
	public List<CommentsM> allselect(Integer Seqno);
	public void Delete(Integer seqno);
}
