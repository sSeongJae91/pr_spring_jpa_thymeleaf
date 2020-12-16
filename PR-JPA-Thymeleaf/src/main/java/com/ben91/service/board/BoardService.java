package com.ben91.service.board;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ben91.domain.Board;
import com.ben91.repository.board.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository br;
	
	public Board save(Board boardVO) {
		
		br.save(boardVO);
		
		return boardVO;
	}

	public List<Board> findBoardByTitle(String title) {
		
		List<Board> list = br.findBoardByTitle(title);
		
		return list;
	}
	
	public Page<Board> list(Pageable page, String type, String keyword){
		return br.boardListPaging(page, type, keyword);
	}
	
	public Optional<Board> findById(Long bno){
		
		Optional<Board> board = br.findById(bno);
		
		return board;
	}
	
	public void delete(Long bno) {
		
		br.deleteById(bno);
	}
}
