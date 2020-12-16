package com.ben91;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.ben91.domain.Board;
import com.ben91.domain.Reply;
import com.ben91.repository.reply.ReplyRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class ReplyRepositoryTest {

	@Autowired
	ReplyRepository repo;
	
	@Test
	public void testInsertReplies() {
		
		Long[] arr = {40L, 50L, 90L};
		
		Arrays.stream(arr).forEach(num -> {
			
			Board board = new Board();
			board.setBno(num);
			
			IntStream.range(0, 10).forEach(i -> {
				Reply replies = new Reply();
				replies.setReplyText("REPLIES.." + i);
				replies.setReplyer("replyer" + i);
				replies.setBoard(board);
				
				repo.save(replies);
			});
			
		});
	}
}
