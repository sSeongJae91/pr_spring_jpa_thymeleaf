package com.ben91.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.ben91.domain.Member;
import com.ben91.domain.MemberRole;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SecurityUser extends User{
	
	private static final String ROLE_PREFIX = "ROLE_";
	
	private Member member;

	public SecurityUser(
			String username, 
			String password, 
			Collection<? extends GrantedAuthority> authorities) {
		
		super(username, password, authorities);
	}
	
	public SecurityUser(Member member) {
		
		super(member.getUid(),
				member.getUpw(), //"{noop}" + member.getUpw() PasswordEncoder 적용 후에는 "{noop}" 생략
				makeGrantedAuthority(member.getRoles()));
		
		this.member = member;
	}
	
	private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles){
		
		List<GrantedAuthority> list = new ArrayList<>();
		
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
		
		return list;
	}

}
