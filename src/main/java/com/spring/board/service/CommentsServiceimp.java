package com.spring.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.CommentsDao;
import com.spring.board.model.CommentsM;

@Repository
public class CommentsServiceimp implements CommentsService {
	@Autowired
	CommentsDao cmdao;

	@Override
	public void Commentsinset(CommentsM Commentsm) {
		cmdao.Commentsinset(Commentsm);

	}

	@Override
	public List<CommentsM> allselect(Integer Seqno) {
		//
		return cmdao.allselect(Seqno);
	}

	@Override
	public void Delete(Integer seqno) {
		cmdao.Delete(seqno);
	}

}
