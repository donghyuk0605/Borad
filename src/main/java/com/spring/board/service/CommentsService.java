package com.spring.board.service;

import java.util.List;

import com.spring.board.model.CommentsM;

public interface CommentsService {
	public void Commentsinset(CommentsM Commentsm);

	public List<CommentsM> allselect(Integer Seqno);

	public void Delete(Integer seqno);
}
