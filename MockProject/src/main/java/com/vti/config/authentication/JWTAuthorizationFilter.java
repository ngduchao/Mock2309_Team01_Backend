package com.vti.config.authentication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.vti.dto.FilmScheduleDTO;
import com.vti.dto.LoginInfoUser;
import com.vti.entity.User;
import com.vti.service.IUserService;
import com.vti.service.JWTTokenService;

public class JWTAuthorizationFilter extends GenericFilterBean {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public void doFilter(
			ServletRequest servletRequest, 
			ServletResponse servletResponse, 
			FilterChain filterChain) throws IOException, ServletException {
	
		Authentication authentication = JWTTokenService.parseTokenToUserInformation((HttpServletRequest) servletRequest);
		
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		
//		User user = userService.findUserByUsername(authentication.getName());
//		
//		LoginInfoUser loginInfoUser = modelMapper.map(user, new TypeToken<LoginInfoUser>() {}.getType());
//		
//		
//		authorities.add(loginInfoUser.getRole());
//		
//		authentication = new UsernamePasswordAuthenticationToken(loginInfoUser.getUsername(), null, authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(servletRequest, servletResponse);
		
	}
}

