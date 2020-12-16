package com.ben91.repository.board;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.ben91.domain.Board;
import com.ben91.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

public interface BoardRepository extends JpaRepository<Board, Long>
											, BoardCustomRepository{
	
	
	public List<Board> findBoardByTitle(String title);

	//findBy를 이용한 특정 컬럼 처리
	public Collection<Board> findByWriter(String writer);
	
	//like %writer%
	public Collection<Board> findByWriterContaining(String writer);
	
	//%title% + or + %content%
	public Collection<Board> findByTitleContainingOrContentContaining(String title, String content);
	
	//부등호 처리 %title% and bno > 
	public Collection<Board> findByTitleContainingAndBnoGreaterThan(String keyword, Long num);
	
	//bno > ? order by bno desc
	public Collection<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);
	
	//bno > ? order by bno desc limit ?, ?
	//pageable if가 적용되는 경우 리턴타입은 slice, page, list 타입
	public List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno, Pageable paging);
	
	//호출 시 정렬과 페이징처리, Page타입 리턴
	public Page<Board> findByBnoGreaterThan(Long bno, Pageable paging);
	
//	//query annotataion 활용, 데이터베이스에 종속적이므로 지양
//	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% AND b.bno > 0 ORDER BY b.bno DESC")
//	public List<Board> findByTitle(String title);
//	
//	public default Predicate makePredicate(String type, String keyword) {
//		
//		BooleanBuilder builder = new BooleanBuilder();
//		
//		QBoard board = QBoard.board;
//		builder.and(board.bno.gt(0));
//		
//		if(type == null) {
//			return builder;
//		}
//		
//		switch(type) {
//		case "t":
//			builder.and(board.title.like("%" + keyword + "%"));
//			break;
//		case "c":
//			builder.and(board.content.like("%" + keyword + "%"));
//			break;
//		case "w":
//			builder.and(board.writer.like("%" + keyword + "%"));			
//			break;
//		}
//		
//		return builder;
//	}
}
