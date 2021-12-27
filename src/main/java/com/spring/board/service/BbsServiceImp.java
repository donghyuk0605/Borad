package com.spring.board.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.BbsDao;
import com.spring.board.model.Condition;
import com.spring.board.model.bbsM;

@Repository
public class BbsServiceImp implements BbsService {
	@Autowired
	BbsDao bbsdao;

	@Override
	public List<bbsM> allresult() {

		return bbsdao.allresult();
	}

	@Override
	public bbsM ViewdetailList(Integer seqno) {
		return bbsdao.ViewdetailList(seqno);
	}

	@Override
	public void Write(bbsM bbsm) {
		bbsdao.Write(bbsm);

	}

	@Override
	public void Delete(HashMap delmap) {
		bbsdao.Delete(delmap);

	}

	@Override
	public void Update(bbsM bbsm) {
		bbsdao.Update(bbsm);

	}

	@Override
	public List<bbsM> pageselect(Integer pagenum) {

		return bbsdao.pageselect(pagenum);
	}

	@Override
	public Integer BbsCount() {
		// TODO Auto-generated method stub
		return bbsdao.BbsCount();
	}

	@Override
	public Integer MaxSeqno() {
		// TODO Auto-generated method stub
		return bbsdao.MaxSeqno();
	}

	@Override
	public Integer MaxGroup() {
		// TODO Auto-generated method stub
		return bbsdao.MaxGroup();
	}

	@Override
	public Integer getRownum(Integer seqno) {
		// TODO Auto-generated method stub
		return bbsdao.getRownum(seqno);
	}

	@Override
	public List<bbsM> getBbsList(Condition c) {
		// TODO Auto-generated method stub
		return bbsdao.getBbsList(c);
	}

	@Override
	public void updateOrderNo(bbsM bbsm) {
		bbsdao.updateOrderNo(bbsm);

	}

}
