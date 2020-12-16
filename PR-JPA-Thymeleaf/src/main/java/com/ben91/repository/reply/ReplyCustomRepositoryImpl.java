package com.ben91.repository.reply;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import com.ben91.domain.Board;
import com.ben91.domain.Reply;
import com.ben91.domain.QReply;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Transactional(readOnly=true)
public class ReplyCustomRepositoryImpl extends QuerydslRepositorySupport implements ReplyCustomRepository{

	public ReplyCustomRepositoryImpl(JPAQueryFactory queryFactory) {
		super(Reply.class);
		this.queryFactory = queryFactory;
	}
	
	private final JPAQueryFactory queryFactory;
	
	
	public List<Reply> getRepliesOfBoard(Board board) {
		
		final QReply reply = QReply.reply;
		
		return  from(reply)
				.where(reply.board.eq(board),
						reply.rno.gt(0L))
				.orderBy(reply.rno.desc())
				.fetch();
	}
}
