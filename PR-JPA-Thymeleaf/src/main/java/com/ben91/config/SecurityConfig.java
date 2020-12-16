package com.ben91.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.ben91.security.LoginSuccessHandler;
import com.ben91.security.UsersService;

import lombok.extern.java.Log;

@Log
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UsersService usersService;
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("security config....");
		
		//특정 권한을 가진 사용자만 해당 경로에 접근 가능
		http.
			authorizeRequests()
				.antMatchers("/guest/**").permitAll()
				.antMatchers("/manager/**").hasRole("MANAGER")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/board/list").permitAll()
				.antMatchers("/boards/register").hasAnyAuthority("BASIC", "MANAGER", "ADMIN");
				//admin or manager 으로 접근할 경우 브라우저는 자동으로 /login으로 이동
		
		http.
			formLogin()
				.loginPage("/login")
				.successHandler(new LoginSuccessHandler());
		
		//403시 redirection처리
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		
		//session 무효화
		http.logout().logoutUrl("/logout").invalidateHttpSession(true);
		
//		http.userDetailsService(usersService);
		http.rememberMe()
			.key("ben")
			.userDetailsService(usersService)
			.tokenRepository(getJDBCRepository())
			.tokenValiditySeconds(60*60*24); //24시간
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		log.info("build Auth global.....");
		
		//jdbc를 이용한 인증 처리
//		String query1 = "SELECT uid username, CONCAT('{noop}', upw) password, true enabled FROM"
//				+ " member where uid=?";
//		
//		String query2 = "SELECT member uid, role_name role FROM member_role WHERE member=?";
//		
//		auth.jdbcAuthentication()
//			.dataSource(dataSource)
//			.usersByUsernameQuery(query1)
//			.rolePrefix("ROLE_")
//			.authoritiesByUsernameQuery(query2);
		
		auth.userDetailsService(usersService).passwordEncoder(passwordEncoder());
	}
	
	
	//remember-me를 통해 토큰관리
	//select * from persistent_logins;
	/*
	 * create table persistent_logins (
	 * 	username varchar(64) not null,
	 *  series varchar(64) not null,
	 *  token varchar(64) not null,
	 *  last_used timestamp not null
	 *  );
	 * */
	private PersistentTokenRepository getJDBCRepository() {
		
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		
		return repo;
	}
	
	//passwordEncoder
	//BCryptPasswordEncoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
