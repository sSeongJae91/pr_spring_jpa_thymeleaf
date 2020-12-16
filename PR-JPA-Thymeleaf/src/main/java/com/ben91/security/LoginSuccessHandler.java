package com.ben91.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import lombok.extern.java.Log;

@Log
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	@Override
	protected String determineTargetUrl(HttpServletRequest request,
			HttpServletResponse response) {
		
		log.info("------------------------------determineTargetUrl------------------------------");
		
		Object dest = request.getSession().getAttribute("dest");
		
		String nextUrl = null;
		//로그인 시 기존 페이지로 이동
		if(dest != null) {
			request.getSession().removeAttribute("dest");
			nextUrl = (String)dest;
		}else {
			nextUrl = super.determineTargetUrl(request, response);
		}
		
		log.info("------------------------------" + nextUrl + "------------------------------");
		
		return nextUrl;
	}
}
