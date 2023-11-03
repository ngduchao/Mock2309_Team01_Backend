package com.vti.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "`User`")
@NoArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@Column(name = "username", length = 50, nullable = false, unique = true, updatable = false)
	private String userName;
	
	@Column(name = "email", length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", length = 800, nullable = false)
	private String password;
	
	@Column(name = "firstname", length = 50, nullable = false, updatable = false)
	private String firstName;

	@Column(name = "lastname", length = 50, nullable = false, updatable = false)
	private String lastName;

	@Column(name = "`role`", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRole role = UserRole.User;
	
	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private UserStatus status = UserStatus.NOT_ACTIVE;
	
	@OneToMany(mappedBy = "user")
	private List<Film> films;
	
	public User(String userName, String email, String password, String firstName, String lastName) {
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
