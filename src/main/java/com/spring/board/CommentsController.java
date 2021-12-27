package com.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.model.CommentsM;
import com.spring.board.service.CommentsService;

@Controller
public class CommentsController {
	@Autowired
	private CommentsService cmService;

	@RequestMapping(value = "/Comments/Commentswrite.do")
	@ResponseBody
	public String Commentswrite(CommentsM Commentsm) {

		cmService.Commentsinset(Commentsm);
		return "SUCCESS";
	}

	@RequestMapping(value = "/Comments/delete.do")
	@ResponseBody
	public String delete(Integer Id) {
		cmService.Delete(Id);
		return "SUCCESS";
	}

}
