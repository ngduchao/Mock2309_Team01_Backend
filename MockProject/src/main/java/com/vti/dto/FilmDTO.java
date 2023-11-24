package com.vti.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilmDTO {
	
	private Integer filmId;
	
	private String name;
	
	private String directors;
	
	private String actors;
	
	private String genre;
	
	private String duration;
	
	private String description;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date releaseDate;
	
	private Integer ticketPrice;
	
	private String poster;
	
	private UserInfoDTO user; //thông tin user dùng 1 DTO để hiển thị
	
	private List<FilmScheduleInfoDTO> filmSchedules;
		
	@Data
	@NoArgsConstructor
	public static class UserInfoDTO {
		
		private Integer id;
		
		private String username;
	}
	
	@Data
	@NoArgsConstructor
	public static class FilmScheduleInfoDTO{
		
		private Integer scheduleId;
		
		@JsonFormat(pattern = "yyyy-MM-dd")
		private Date timeSlot;
	}
}

