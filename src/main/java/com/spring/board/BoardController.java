package com.spring.board;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.model.CommentsM;
import com.spring.board.model.Condition;
import com.spring.board.model.bbsM;
import com.spring.board.service.BbsService;
import com.spring.board.service.CommentsService;

@Controller
public class BoardController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Board() {

		return "index";
	}

	@Autowired
	private BbsService bbsService;

	@Autowired
	private CommentsService cmService;

	@RequestMapping(value = "/Board/bbsListView.do")
	public ModelAndView bbsListView(Integer PAGE_NUM, Integer SEQNO1) {
		System.out.println("bbsListView");
		System.out.println(PAGE_NUM);
		// 게시글 목록을 출력하는 메서드
		if (PAGE_NUM == null)
			PAGE_NUM = 1;
		ModelAndView mav = new ModelAndView("/bbsListView");
		// 이미지 게시글 목록을 출력하는 메서드
		if (SEQNO1 != null) {
			// 해당 글번호로 일련번호를 찾는다.(오라클 쿼리)
			int rownum = bbsService.getRownum(SEQNO1);
			// 페이지번호 = 일련번호 / 5;
			int page = rownum / 5;
			// if(일련번호 % 5 != 0) 페이지번호++;
			if (rownum % 5 != 0)
				page++;
			// PAGE_NUM = 페이지번호;
			PAGE_NUM = page;
		}

		int currentPage = PAGE_NUM;// 현재 페이지 설정
		int totalPageCount = 0;// 페이지 갯수용 변수
		int startRow = 0;
		int endRow = 0;// 범위 조회를 위한 변수
		int count = bbsService.BbsCount();// 전체 글 갯수 검색
		if (count > 0) {
			// 페이지 갯수를 찾는다.
			totalPageCount = count / 5;
			if (count % 5 > 0)
				totalPageCount++;
			startRow = (currentPage - 1) * 5 + 1;// 검색 시작 일련번호
			endRow = currentPage * 5;// 검색 종료 일련번호
			if (endRow > count)
				endRow = count;
			// 검색 종료 일련번호가 글 갯수보다 클 수 없으므로,
			// 큰 경우에는 검색 종료 일련번호를 글 갯수로 설정
		}
		Condition c = new Condition();
		c.setStartRow(startRow);
		c.setEndRow(endRow);
		System.out.println(startRow);
		System.out.println(endRow);
		List<bbsM> bbsm = bbsService.getBbsList(c);
		
		System.out.println(endRow);
		for(bbsM test :bbsm ) {
			System.out.println(test.getTitle());
		}
		mav.addObject("LIST", bbsm);// 게시글 목록 저장
		mav.addObject("COUNT", count);// 글의 갯수 저장
		mav.addObject("STARTROW", startRow);// 글의 갯수 저장
		mav.addObject("ENDROW", endRow);// 목록의 종료 일련번호 저장
		mav.addObject("pageCount", totalPageCount);// 글의 갯수 저장
		mav.addObject("currentPage", currentPage);// 현재 페이지 저장
		return mav;

	}

	@RequestMapping(value = "/Board/writeForm.do")
	public ModelAndView writeForm(bbsM bbs) {
		System.out.println("writeForm");
		ModelAndView mav = new ModelAndView();

		Integer parentId = bbs.getParent_id();
		Integer groupId = bbs.getGroup_id();
		System.out.println("그룹아이디" + groupId);
		System.out.println("부모아이디" + parentId);
		if (parentId != null) {
			String title = "";
			System.out.println("그룹아이디" + groupId);
			bbsM bbsm = bbsService.ViewdetailList(parentId);
			if (bbsm != null)
				title = "RE]" + bbsm.getTitle();
			mav.addObject("parId",parentId);
			mav.addObject("bbsm", bbsm);
			mav.addObject("title", title);
			mav.setViewName("/writeForm");
			return mav;
		}
		mav.setViewName("/writeForm");
		return mav;
	}

	@RequestMapping(value = "/Board/wirte.do")
	@ResponseBody
	public String write(bbsM bbsm, Integer order_no, Integer group_id, Integer parent_id, HttpSession session) {
		System.out.println("wirte1");
		System.out.println(bbsm.getTitle());
		Integer maxId = bbsService.MaxSeqno();
		System.out.println(bbsm.getFile());
		System.out.println("그룹:"+group_id);
		System.out.println("부모:"+parent_id);
		

		if (maxId == null)
			maxId = 0;

		if (bbsm.getParent_id() == null) {// 원글인 경우
			bbsm.setParent_id(0);// 부모글 번호 0
			bbsm.setOrder_no(0);// 순서번호 0
			Integer maxGid = bbsService.MaxGroup();
			if (maxGid == null)
				maxGid = 0;
			bbsm.setGroup_id(maxGid + 1);
		} else {// 답글인 경우
			bbsm.setParent_id(parent_id);
			bbsm.setGroup_id(group_id);
			bbsm.setOrder_no(order_no);
			bbsService.updateOrderNo(bbsm);
		}

		bbsm.setSeqno(maxId + 1);

		MultipartFile multiFile = bbsm.getFile();
		String fileName = null;
		String path = null;
		OutputStream out = null; // 쓰는 객체
		if (multiFile != null) { // 파일이 존재하는 경우
			System.out.println("multiFile!=null");
			fileName = multiFile.getOriginalFilename();
			ServletContext ctx = session.getServletContext();
			path = ctx.getRealPath("/upload/" + fileName);
			try {
				out = new FileOutputStream(path);
				BufferedInputStream bis = new BufferedInputStream(multiFile.getInputStream());
				byte[] buffer = new byte[8156];
				int read = 0;
				while ((read = bis.read(buffer)) > 0) {
					out.write(buffer, 0, read);
				}
				if (out != null)
					out.close();
				if (bis != null)
					bis.close();
			} catch (Exception e) {
				e.printStackTrace();
			} // 파일 설정 후 저장
			System.out.println("fileName:" + fileName);
			System.out.println("path경로:" + path);
			bbsm.setFile_url(fileName); // 파일명을 설정

		} else {
			bbsm.setFile_url("없음");
		}
		bbsService.Write(bbsm);
		return "SUCCESS";

	}

	@RequestMapping(value = "/Board/viewDetail.do")
	public ModelAndView viewDetail(Integer SEQNO) {
		bbsM bbsm = bbsService.ViewdetailList(SEQNO);
		List<CommentsM> Commentslist = cmService.allselect(SEQNO);
		ModelAndView mav = new ModelAndView("/viewDetail");
		mav.addObject("COMMENTSLIST", Commentslist);
		mav.addObject("BBSDETILList", bbsm);
		return mav;
	}

	@RequestMapping(value = "/Board/delete.do")
	@ResponseBody
	public String delete(Integer Id,Integer o_no, HttpSession session) {
		System.out.println("테스트");
		System.out.println(o_no+"+"+Id);
		HashMap delmap = new HashMap<String, Object>();
		delmap.put("delid", Id);
		delmap.put("delo_no", o_no);

		bbsM bbsm = bbsService.ViewdetailList(Id);
		String fileName = bbsm.getFile_url();
		String path = null;
		

		if (fileName != null) {
			ServletContext ctx = session.getServletContext();
			path = ctx.getRealPath("/upload/");

			Path filePath = FileSystems.getDefault().getPath(path, fileName);
			try {
				Files.delete(filePath);
			} catch (IOException | SecurityException e) {
				bbsService.Delete(delmap);
				return "SUCCESS";
			}
		}
		bbsService.Delete(delmap);
		return "SUCCESS";

	}

	@RequestMapping(value = "/Board/updateFrom.do")
	public ModelAndView updateFrom(Integer Id) {
		System.out.println("updateFrom");
		bbsM bbsm = bbsService.ViewdetailList(Id);
		ModelAndView mav = new ModelAndView("/updateFrom");
		mav.addObject("bbsm", bbsm);
		return mav;
	}

	@RequestMapping(value = "/Board/update.do")
	@ResponseBody
	public String update(bbsM bbsm, HttpSession session) {
		System.out.println(bbsm.getTitle());
		bbsM old = bbsService.ViewdetailList(bbsm.getSeqno());// 기존의 글 검색
		MultipartFile multiFile = bbsm.getFile();
		System.out.println("기존글번호:" + bbsm.getSeqno());
		System.out.println("기존파일명:" + old.getFile_url());
		String fileName = "";
		String oldfileName= old.getFile_url();
		try {
			fileName = multiFile.getOriginalFilename();
		} catch (Exception e) {
			bbsm.setFile_url(old.getFile_url());
			bbsService.Update(bbsm);
			return "SUCCESS";
		}

		ServletContext ctx = session.getServletContext();
		String path = ctx.getRealPath("/upload/" + fileName);
		String oldpath=ctx.getRealPath("/upload/" + oldfileName);
		Path odlfilepath = FileSystems.getDefault().getPath(oldpath);
		OutputStream os = null;
		try {
			os = new FileOutputStream(path);
			BufferedInputStream bis = new BufferedInputStream(multiFile.getInputStream());
			byte[] buffer = new byte[8106];
			int read = 0;
			Files.delete(odlfilepath);
			while ((read = bis.read(buffer)) > 0) {
				os.write(buffer, 0, read);
			}
			if (os != null)
				os.close();
			if (bis != null)
				bis.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		bbsm.setFile_url(fileName);// 새이름으로 설정

		// DB에서 변경

		bbsService.Update(bbsm);
		return "SUCCESS";
	}

}
