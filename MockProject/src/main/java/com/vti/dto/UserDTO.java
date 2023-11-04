package com.vti.dto;

import java.util.List;

import com.vti.entity.Ticket.TicketKey;
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
	
	private String role;
	
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
	}
}
