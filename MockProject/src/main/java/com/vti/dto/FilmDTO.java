package com.vti.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vti.entity.Film;
import com.vti.entity.FilmSchedule;

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
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date releaseDate;
	
	private Integer ticketPrice;
	
	private String poster;
	
	private Integer creatorId;
	
	private String username;
	
	private List<FilmScheduleDTO> filmSchedules;
	
}
