package com.ben91;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.ben91.domain.Member;
import com.ben91.domain.MemberRole;
import com.ben91.repository.MemberRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class MemberTests {

	@Autowired
	private MemberRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Test
//	public void testInsert() {
//		
//		for (int i = 0; i < 100; i++) {
//			
//			Member member = new Member();
//			member.setUid("user" + i);
//			member.setUpw("pw" + i);
//			member.setUname("USERNAME" + i);
//			
//			MemberRole role = new MemberRole();
//			
//			if(i<=80) {
//				role.setRoleName("BASIC");
//			}else if(i <= 90) {
//				role.setRoleName("MANAGER");
//			}else {
//				role.setRoleName("ADMIN");
//			}
//			List<MemberRole> listRole = new ArrayList<MemberRole>();
//			listRole.add(role);
//			member.setRoles(listRole);
//			repo.save(member);
//		}
//	}
//	@Test
//	public void testRead() {
//		Optional<Member> result = repo.findById("user85");
//		
//		result.ifPresent(member -> log.info("member" + member));
//	}
	@Test
	public void testUpdatePw() {
		List<String> ids = new ArrayList<>();
		
		for(int i=0; i<=100; i++) {
			ids.add("user"+i);
		}
		
		repo.findAllById(ids).forEach(member -> {
			member.setUpw(passwordEncoder.encode(member.getUpw()));
			
			repo.save(member);
		});
		
		
	}
}
