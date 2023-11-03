package com.vti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "`Film_Schedule`")
public class FilmSchedule implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "schedule_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short scheduleId;

	@Column(name = "`seat_number`", length = 100, nullable = false, unique = true)
	private String seatNumber;

	@Column(name = "time_slot")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date timeSlot;
	
	@ManyToOne
	@JoinColumn(name="film_id")
	private Film film;
}
