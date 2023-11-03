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

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`User`")
@Data
@NoArgsConstructor
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "user_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "`username`", length = 50, nullable = false, unique = true)
	private String username;
	
	@Column(name = "`email`", length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(name = "`password`", length = 50, nullable = false)
	private String password;
	
	@Column(name = "`firstName`", length = 50, nullable = false)
	private String firstName;
	
	@Column(name = "`lastName`", length = 50, nullable = false)
	private String lastName;
	
	@Formula("concat(firstName, ' ', lastName)")
	private String fullName;
	
	@Column(name = "role", nullable = false)
	private String role = "User";
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "`status`", nullable = false)
	private UserStatus status = UserStatus.NOT_ACTIVE;
	
//	@JsonBackReference
	@OneToMany(mappedBy = "user")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Film> films;
	
	@JsonBackReference
	@OneToMany(mappedBy = "user1")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Ticket> tickets;
	
}
