package com.vti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vti.entity.User;
import com.vti.entity.UserStatus;

public interface IUserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{
	
	public boolean existsByUsername(String username);
	
	public boolean existsByEmail(String email);
	
	public User findByUsername(String username);
	
	public User findByEmail(String email);
	
	@Query("	SELECT 	status 		"
			+ "	FROM 	User 		"
			+ " WHERE 	email = :email")
	public UserStatus findStatusByEmail(@Param("email") String email);
	
	public void deleteByIdIn(List<Integer> ids);
}
