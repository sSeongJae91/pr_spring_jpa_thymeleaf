package com.ben91;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;import org.springframework.web.bind.support.WebBindingInitializer;

import com.ben91.domain.Board;
import com.ben91.repository.board.BoardCustomRepositoryImpl;
import com.ben91.repository.board.BoardRepository;
import com.ben91.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class BoarRepositoryTest {

	@Autowired
	private BoardRepository boardRepo;
	
	
//	@Test
//	public void testInsert() {
//		
//		for (int i = 1; i <= 100; i++) {
//			System.out.println("### : " + i);
//						
//			BoardVO board = new BoardVO();
//			board.setTitle(new String("test " + i));
//			board.setContent(new String("content " + i + " ddddd"));
//			board.setWriter("jsj");
//			
//			boardRepo.save(board);
//		}
//		
//	}
	
//	@Test
//	public void testByWriter() {
//		
//		Collection<BoardVO> results = boardRepo.findByWriter("jsj");
//		
//		results.forEach(
//				board -> System.out.println(board));
//	}
	
//	@Test
//	public void testBnoOrderByPaging() {
//		
//		//spring boot 2.0.0
//		Pageable paging = PageRequest.of(0, 10);
//		
//		Collection<BoardVO> results = boardRepo.findByBnoGreaterThanOrderByBnoDesc(0L, paging);
//		
//		results.forEach(board -> System.out.println(board));
//	}
	
//	@Test
//	public void testBnoPagingSort() {
//
//		//spring boot 2.0.0
//		Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");
//		
//		Page<Board> result = boardRepo.findByBnoGreaterThan(0L, paging);
//		
//		List<Board> list = result.getContent();
//		
//		list.forEach(board -> System.out.println(board));
//	}
	
    @Test
    public void findQuerydsl() {
    	List<Board> result = boardRepo.findRecentTitle("test 1");
    	
    	result.forEach(b -> System.out.println(b));
    	assertThat(result.size()).isLessThan(11);
    }
    
    @Test
    public void findQuerydsl2() {
    		
    	Pageable paging = PageRequest.of(0, 10, Sort.Direction.ASC, "bno");
    	Page<Board> result = boardRepo.findAll(paging);
    	
    	List<Board> bList = result.getContent();
    	
    	bList.forEach(b -> System.out.println(b));
    }
}
