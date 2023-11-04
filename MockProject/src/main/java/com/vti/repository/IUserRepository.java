package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.vti.entity.User;
import com.vti.form.user.UpdatingUserForm;

public interface IUserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{
	
	public boolean existsByUsername(String username);
	
	public boolean existsByEmail(String email);
}
