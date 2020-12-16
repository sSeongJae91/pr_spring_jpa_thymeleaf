package com.ben91.controller.member;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ben91.domain.Member;
import com.ben91.service.member.MemberService;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/member")
public class MemberController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseEntity<Member> save(@RequestBody Member member){
		return new ResponseEntity<Member>(memberService.save(member), HttpStatus.OK);
	}
	
	@GetMapping("/join")
	public void join() {
		
	}
	
	@Transactional
	@PostMapping("/join")
	public String joinPost(@ModelAttribute("member")Member member) {
		
		log.info("Member : " + member);
		
		String encryptPw = pwEncoder.encode(member.getUpw());
		log.info("en : " + encryptPw);
		
		member.setUpw(encryptPw);
		
		memberService.save(member);
		
		return "/member/joinResult";
	}
}
