package com.ben91;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.ben91.domain.Member;
import com.ben91.domain.Profile;
import com.ben91.repository.MemberRepository;
import com.ben91.repository.ProfileRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class ProfileTest {

	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	ProfileRepository profileRepo;
	
//	@Test
//	public void testInsertMembers() {
//		IntStream.range(1, 101).forEach(i -> {
//			Member member = new Member();
//			member.setUid("user" + i);
//			member.setUpw("pw" + i);
//			member.setUname("uname" + i);
//			
//			memberRepo.save(member);
//		});
//	}
	
//	@Test
//	public void testInsertProfile() {
//		Member member = new Member();
//		member.setUid("user1");
//		
//		for (int i = 1; i < 5; i++) {
//			
//			Profile profile1 = new Profile();
//			profile1.setFname("face" + i + ".jpg");
//			
//			if(i == 1) {
//				profile1.setCurrent(true);
//			}
//			profile1.setMember(member);
//			
//			profileRepo.save(profile1);
//		}
//	}
	
	@Test
	public void testFetchJoin() {
		List<Object[]> result = memberRepo.getMemberWithProfile("user1");
		
		result.forEach(arr -> System.out.println(Arrays.toString(arr)));
	}
}
