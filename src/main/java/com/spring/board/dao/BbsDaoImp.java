package com.spring.board.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.model.Condition;
import com.spring.board.model.bbsM;



@Repository
public class BbsDaoImp implements BbsDao {

	@Autowired
	SqlSession session;

	public List<bbsM> allresult() {
		List<bbsM> list = session.selectList("Allresult");
		return list;
	}

	public void Write(bbsM bbsm) {
		session.insert("Write", bbsm);

	}

	public bbsM ViewdetailList(Integer seqno) {
		bbsM bbsm = session.selectOne("ViewDetail", seqno);
		return bbsm;
	}

	public void Delete(HashMap del) {
		session.delete("Delete", del);

	}

	public void Update(bbsM bbsm) {
		session.update("Update", bbsm);

	}

	public List<bbsM> pageselect(Integer pagenum) {
		List<bbsM> list = session.selectList("pageselect", pagenum);
		return list;
	}

	public Integer BbsCount() {
		Integer count = session.selectOne("BbsCount");
		return count;
	}

	public Integer MaxSeqno() {
		Integer max = session.selectOne("MaxSeqno");
		return max;

	}

	public Integer MaxGroup() {
		Integer max = session.selectOne("MaxGroup");
		return max;
	}

	public Integer getRownum(Integer seqno) {
		Integer rownum = session.selectOne("MaxGroup");
		return rownum;
	}

	public List<bbsM> getBbsList(Condition c) {
		return session.selectList("getbbsReplyCondition", c);
	}

	@Override
	public void updateOrderNo(bbsM bbsm) {
		session.update("updateOrderNoReply",bbsm);
	}
	
	

}
