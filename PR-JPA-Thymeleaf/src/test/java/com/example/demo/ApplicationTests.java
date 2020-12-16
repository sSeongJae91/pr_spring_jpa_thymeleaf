package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ben91.domain.Board;
import com.ben91.repository.board.BoardRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	
	@Autowired
	BoardRepository br;
	
	@Test
	@DisplayName("insert 100 board successful")
	public void create_new_board() {
		System.out.println("insert 시작");
		for (int i = 3; i <= 100; i++) {
			System.out.println("### : " + i);
			
			Board boardVO = Board.builder()
								.title("제목..." + i)
								.content("내용 ..." +i + "채우기")
								.writer("jsj")
								.build();
			
			assertNotNull(boardVO);
			assertEquals(1, 1);
			
			br.save(boardVO);
		}
		System.out.println("insert 종료");
	}

}
