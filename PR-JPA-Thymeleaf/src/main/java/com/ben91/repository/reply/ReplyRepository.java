package com.ben91.repository.reply;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ben91.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>, ReplyCustomRepository{

}
