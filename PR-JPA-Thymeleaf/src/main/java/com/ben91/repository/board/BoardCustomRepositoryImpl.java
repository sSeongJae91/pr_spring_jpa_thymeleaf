package com.ben91.repository.board;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ben91.domain.Board;
import com.ben91.domain.QBoard;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Transactional(readOnly = true)
public class BoardCustomRepositoryImpl extends QuerydslRepositorySupport implements BoardCustomRepository{
	
	public BoardCustomRepositoryImpl() {
		super(Board.class);
	}

	@Override
	public List<Board> findRecentTitle(String title) {
		
		final QBoard board = QBoard.board;
		
		return from(board)
				.where(board.title.eq(title))
				.orderBy(board.bno.desc())
				.fetch();
	}
	
	@Override
	public Page<Board> boardListPaging(Pageable page, String type, String keyword) {
		
		final QBoard board = QBoard.board;
		
		QueryResults<Board> result;
		
		
		if(type==null) {
			result = from(board)
					.offset(page.getOffset())
					.orderBy(board.bno.desc())
					.limit(page.getPageSize())
					.fetchResults();
		}else {
			switch(type){
				case "t":
					result = from(board)
					.where(board.title.contains(keyword))
					.orderBy(board.bno.desc())
					.offset(page.getOffset())
					.limit(page.getPageSize())
					.fetchResults();
					break;
				
				case "c":
					result = from(board)
					.where(board.content.contains(keyword))
					.orderBy(board.bno.desc())
					.offset(page.getOffset())
					.limit(page.getPageSize())
					.fetchResults();
					break;
				
				case "w":
					result = from(board)
					.where(board.writer.contains(keyword))
					.orderBy(board.bno.desc())
					.offset(page.getOffset())
					.limit(page.getPageSize())
					.fetchResults();
					break;
				
				default :
					result = from(board)
					.offset(page.getOffset())
					.orderBy(board.bno.desc())
					.limit(page.getPageSize())
					.fetchResults();
					break;
			}
		}
		
		return new PageImpl<Board>(result.getResults(), page, result.getTotal());
	}
}
