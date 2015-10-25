package org.zerock.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception{
		logger.info("register get...");
		logger.info("model::" +model.toString());

	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPOST(BoardVO board, Model model) throws Exception{
		logger.info("register post...");
		
		boardService.regist(board);
		
		model.addAttribute("result", "success");
		
		return "/board/success";
	}
	
}
