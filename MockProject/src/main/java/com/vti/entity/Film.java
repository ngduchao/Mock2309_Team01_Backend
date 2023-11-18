package com.vti.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`Film`")
@Data
@NoArgsConstructor
public class Film implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "film_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer filmId;
	
	@Column(name = "name", length = 100, unique = true, nullable = false)
	private String name;
	
	@Column(name = "directors", length = 50, nullable = false)
	private String directors;
	
	@Column(name = "actors", length = 200, nullable = false)
	private String actors;
	
	@Column(name = "genre", length = 100, nullable = false)
	private String genre;
	
	@Column(name = "duration", length = 30, nullable = false)
	private String duration;
	
	@Column(name = "description", length = 200, nullable = false)
	private String description;
	
	@Column(name = "release_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date releaseDate;
	
	@Column(name = "ticket_price", nullable = false)
	private Integer ticketPrice;
	
	@Column(name = "poster", nullable = false)
	private String poster;
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "`creator_id`")
	private User user;
	
	
	@OneToMany(mappedBy = "film")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<FilmSchedule> filmSchedules;

	public Film(String name, String directors, String actors, String genre, String duration, String description, Date releaseDate,
			 Integer ticketPrice, String poster) {
		super();
		this.name = name;
		this.directors = directors;
		this.actors = actors;
		this.genre = genre;
		this.duration = duration;
		this.description = description;
		this.releaseDate = releaseDate;
		this.ticketPrice = ticketPrice;
		this.poster = poster;
	}

	public Film(String name, String directors, String actors, String genre, String duration, String description,
			Integer ticketPrice, String poster) {
		super();
		this.name = name;
		this.directors = directors;
		this.actors = actors;
		this.genre = genre;
		this.duration = duration;
		this.description = description;
		this.ticketPrice = ticketPrice;
		this.poster = poster;
	}
	
	
	
}
