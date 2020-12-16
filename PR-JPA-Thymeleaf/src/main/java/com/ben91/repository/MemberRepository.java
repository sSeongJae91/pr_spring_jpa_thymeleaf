package com.ben91.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ben91.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String>{

	@Query("SELECT m, p FROM Member m LEFT OUTER JOIN Profile p " +
			"ON m.id = p.member WHERE m.uid=?1 AND p.current = true")
			public List<Object[]> getMemberWithProfile(String uid);
}
