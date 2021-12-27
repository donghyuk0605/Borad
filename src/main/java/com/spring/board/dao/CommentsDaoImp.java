package com.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.model.CommentsM;





@Repository
public class CommentsDaoImp implements CommentsDao {

	@Autowired
	private SqlSession session;

	public void Commentsinset(CommentsM Commentsm) {
		session.insert("CommentsWrite",Commentsm);
	}
	
	public List<CommentsM> allselect(Integer SEQNO) {
		List<CommentsM> list = session.selectList("CommentsList",SEQNO);
		return list;
	}
	
	public void Delete(Integer SEQNO) {
		session.delete("Commentsdelete",SEQNO);
		
	}


}
