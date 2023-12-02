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

import com.vti.dto.UserDTO.FilmInfoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "`Film_Schedule`")
public class FilmSchedule implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "schedule_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer scheduleId;
	
	@Column(name = "seat_number", nullable = false)
	private Integer seatNumber;
	
	@Column(name = "time_slot", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
//	@CreationTimestamp
	private Date timeSlot;
	
//	@JsonBackReference
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "film_id")
	private Film film;
	
//	@JsonBackReference
	@OneToMany(mappedBy = "filmSchedule")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Ticket> tickets;

	public FilmSchedule(Integer seatNumber, Date timeSlot, Film film) {
		super();
		this.seatNumber = seatNumber;
		this.timeSlot = timeSlot;
		this.film = film;
	}

	public FilmSchedule(Integer seatNumber, Date timeSlot) {
		super();
		this.seatNumber = seatNumber;
		this.timeSlot = timeSlot;
	}

	
	
}
