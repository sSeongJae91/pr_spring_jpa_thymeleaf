package com.ben91.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ben91.domain.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long>{

	
	@Query("SELECT m, p FROM Member m LEFT OUTER JOIN Profile p " +
	"ON m.id = p.member WHERE m.uid=?1 AND p.current = true")
	public List<Object[]> getMemberWithProfile(String uid);
}
