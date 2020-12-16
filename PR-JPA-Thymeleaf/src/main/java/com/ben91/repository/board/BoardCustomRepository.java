package com.ben91.repository.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ben91.domain.Board;

public interface BoardCustomRepository {

	List<Board> findRecentTitle(String titile);
	Page<Board> boardListPaging(Pageable page, String type, String keyword);
}
