package com.vti.filter;

import com.vti.entity.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserFilterForm {
	
	private Role role;
}
