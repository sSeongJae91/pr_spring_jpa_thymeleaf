package com.ben91.controller.board;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ben91.domain.Board;
import com.ben91.service.board.BoardService;
import com.ben91.vo.PageMaker;
import com.ben91.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/board")
@Log
public class BoardController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseEntity<Board> save(@RequestBody Board member){
		return new ResponseEntity<Board>(boardService.save(member), HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public void list(PageVO vo, Model model) {
		
		Pageable page = vo.makePageable(0, "bno");
		
		Page<Board> result = boardService.list(page, vo.getType(), vo.getKeyword());
		
		log.info(" " + page);
		log.info("" + result);
		
		log.info("get Total Page Number : " +  result.getTotalPages());
		
		model.addAttribute("result", new PageMaker(result));
	}
	
	@GetMapping("/register")
	public void registerGET(@ModelAttribute("vo")Board vo) {
		log.info("register page");
	}
	
	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo")Board vo, RedirectAttributes rttr){
		
		boardService.save(vo);
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/view")
	public void view(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		log.info("bno" + bno);
		
		boardService.findById(bno).ifPresent(board -> model.addAttribute("vo", board));
	}
	
	@Secured(value= {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
	@GetMapping("/modify")
	public void modify(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		
		log.info("modify bno : " + bno);
		
		boardService.findById(bno).ifPresent(board -> model.addAttribute("vo", board));
	}
	
	@Secured(value= {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
	@PostMapping("/delete")
	public String delete(Long bno, PageVO vo, RedirectAttributes rttr) {
		
		log.info("delete bno : " + bno);
		
		try {
			boardService.delete(bno);
			rttr.addFlashAttribute("msg", "success");
			rttr.addAttribute("page", vo.getPage());
			rttr.addAttribute("size", vo.getSize());
			rttr.addAttribute("type", vo.getType());
			rttr.addAttribute("keyword", vo.getKeyword());
		} catch (Exception e) {
			rttr.addFlashAttribute("msg", "failure");
		}
		
		return "redirect:/board/list";
	}
	
	@Secured(value= {"ROLE_BASIC", "ROLE_MANAGER", "ROLE_ADMIN"})
	@PostMapping("/modify")
	public String modifyPost(Board board, PageVO vo, RedirectAttributes rttr) {
		
		log.info("modify bno : " + board.getBno());
		
		boardService.findById(board.getBno()).ifPresent( origin -> {
			
			origin.setTitle(board.getTitle());
			origin.setContent(board.getContent());
			
			boardService.save(origin);
			
			rttr.addFlashAttribute("msg", "success");
			rttr.addAttribute("bno", origin.getBno());
		});
		
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());
		
		return "redirect:/board/view";
	}
}
