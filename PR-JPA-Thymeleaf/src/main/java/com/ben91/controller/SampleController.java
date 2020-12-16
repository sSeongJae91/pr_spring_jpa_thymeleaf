package com.ben91.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ben91.domain.Member;

import lombok.extern.java.Log;

@Controller
@Log
public class SampleController {

	@GetMapping("/sample1")
	public void sample1(Model model) {
		model.addAttribute("greeting", "안녕하세요");
	}
	
	@GetMapping("/sample2")
	public void sample2(Model model) {
		List<Member> list = new ArrayList<>();
		
//		Timestamp time = new Timestamp(System.currentTimeMillis());
//		
//		for (int i = 0; i < 10; i++) {
//			list.add(new Member("id" + i, "pw" + i, "name" + i
//					, time, time));
//		}
		
		model.addAttribute("list", list);
	}
	
	@GetMapping("/sample3")
	public void sample3(Model model) {
		String result = "SUCCESS";
		
		model.addAttribute("result", result);
	}
	
	@GetMapping("/sample4")
	public void sample4(Model model) {
		
		model.addAttribute("now", new Date());
		model.addAttribute("price", 12345678);
		model.addAttribute("title", "This is a just sample.");
		model.addAttribute("options", Arrays.asList("AAAA", "BBBB", "CCCC", "DDDD"));
	}
	
	@GetMapping("/sample/hello")
	public void hello() {
		
	}
	
	@GetMapping("/")
	public String index() {
		log.info("index");
		
		return "index";
	}
	
	
}
