package com.ben91.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ben91.repository.MemberRepository;

import lombok.extern.java.Log;

@Service
@Log
public class UsersService implements UserDetailsService{

	@Autowired
	MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		return memberRepo.findById(username)
				.filter(m -> m!= null)
				.map(m -> new SecurityUser(m)).get();
	}
	
}
