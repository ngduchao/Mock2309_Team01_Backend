package com.vti.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
	Admin, Manager, User;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return name();
	}
}
