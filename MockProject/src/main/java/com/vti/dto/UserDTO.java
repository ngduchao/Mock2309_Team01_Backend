package com.vti.dto;

import java.util.Date;
import java.util.List;

import com.vti.entity.Ticket.TicketKey;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.entity.Role;
import com.vti.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
	
	private Integer id;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private Role role;
	
	private List<FilmInfoDTO> films;
	
	private List<TicketDTO> tickets;
	
	@Data
	@NoArgsConstructor
	public static class FilmInfoDTO{
		
		private Integer filmId;
		
		private String name;
	}
	
	@Data
	@NoArgsConstructor
	public static class TicketDTO{
		
		private TicketKey id;
		
		private Integer quantity;
		
		private Integer total;
		
		@JsonFormat(pattern = "yyyy-MM-dd")
		private Date bookingDate;
	}
	
	public User toEntity() {
		return new User(username, email, password, firstName, lastName);
	}
}
