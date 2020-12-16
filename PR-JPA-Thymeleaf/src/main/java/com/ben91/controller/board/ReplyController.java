package com.ben91.controller.board;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ben91.domain.Board;
import com.ben91.domain.Reply;
import com.ben91.repository.reply.ReplyRepository;
import com.ben91.service.ReplyService;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/replies/*")
@Log
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	@Transactional
	@PostMapping("/{bno}")
	public ResponseEntity<List<Reply>> addReply(@PathVariable("bno")Long bno, @RequestBody Reply reply){
		
		log.info("add reply");
		log.info("bno : " + bno);
		
		Board board = new Board();
		board.setBno(bno);
		
		reply.setBoard(board);
		
		replyService.save(bno, reply);
		
		return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
	}
	
	private List<Reply> getListByBoard(Board board) throws RuntimeException{
		
		log.info("getListByBoard..." + board);
		
		return replyService.getRepliesOfBoard(board);
	}
	
	@Transactional
	@DeleteMapping("/{bno}/{rno}")
	public ResponseEntity<List<Reply>> remove(@PathVariable("bno")Long bno, @PathVariable("rno") Long rno){
		
		log.info("delete reply : " + rno);
		
		replyService.deleteById(rno);
		
		Board board = new Board();
		board.setBno(bno);
		
		return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping("/{bno}")
	public ResponseEntity<List<Reply>> modify(@PathVariable("bno")Long bno, @RequestBody Reply reply){
		
		log.info("modify reply : " + reply);
		
		replyService.findById(reply.getRno()).ifPresent(origin -> {
			origin.setReplyText(reply.getReplyText());
			replyService.save(origin);
		});
		
		Board board = new Board();
		board.setBno(bno);
		
		return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);
	}
	
	@GetMapping("/{bno}")
	public ResponseEntity<List<Reply>> getReplies(@PathVariable("bno")Long bno){
		
		log.info("get all replies");
		
		Board board = new Board();
		board.setBno(bno);
		
		return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
	}
}
