package com.ben91.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ben91.domain.Board;
import com.ben91.domain.Reply;
import com.ben91.repository.reply.ReplyRepository;

@Service
public class ReplyService {
	
	@Autowired
	private ReplyRepository replyRepo;

	public void save(Long bno, Reply reply) {
		
		replyRepo.save(reply);
	}
	
	public void save(Reply reply) {
		
		replyRepo.save(reply);
	}
	
	public List<Reply> getRepliesOfBoard(Board board) {
		
		return replyRepo.getRepliesOfBoard(board);
	}
	
	public void deleteById(Long rno) {
		replyRepo.deleteById(rno);
	}
	
	public Optional<Reply> findById(Long rno){
		return replyRepo.findById(rno);
	}
}
