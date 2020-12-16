package com.ben91.repository.reply;

import java.util.List;

import com.ben91.domain.Board;
import com.ben91.domain.Reply;

public interface ReplyCustomRepository {
	
	public List<Reply> getRepliesOfBoard(Board board);

}
