package com.ben91.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ben91.domain.Member;
import com.ben91.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	public Member save(Member member) {
		memberRepository.save(member);
		
		return member;
	}
}
