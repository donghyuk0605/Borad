package com.spring.board.service;

import java.util.HashMap;
import java.util.List;

import com.spring.board.model.Condition;
import com.spring.board.model.bbsM;

public interface BbsService {
	public List<bbsM> allresult();

	public bbsM ViewdetailList(Integer seqno);

	public void Write(bbsM bbsm);

	public void Delete(HashMap delmap);

	public void Update(bbsM bbsm);

	public List<bbsM> pageselect(Integer pagenum);

	public Integer BbsCount();

	public Integer MaxSeqno();

	public Integer MaxGroup();

	Integer getRownum(Integer seqno);

	public List<bbsM> getBbsList(Condition c);
	
	public void updateOrderNo(bbsM bbsm);

	
}
