package com.vti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Ticket")
@Data
@NoArgsConstructor
public class Ticket implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TicketKey id;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Column(name = "total", nullable = false)
	private Integer total;
	
	@Column(name = "booking_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date bookingDate;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status", nullable = false)
	private PayStatus status = PayStatus.PAY;
	
	@JsonBackReference
	@ManyToOne
	@MapsId("creator_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "creator_id")
	private User user1;
	
	@JsonBackReference
	@ManyToOne
	@MapsId("schedule_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "schedule_id")
	private FilmSchedule filmSchedule;

	
	public Ticket(Integer quantity) {
		super();
		this.quantity = quantity;
	}

	public Ticket(Integer quantity, Integer total, Integer creatorId, Integer scheduleId) {
		super();
		this.quantity = quantity;
		this.total = total;
		TicketKey ticketKey = new TicketKey();
		ticketKey.setCreatorId(creatorId);
		ticketKey.setScheduleId(scheduleId);
	}

	@Embeddable
	public static class TicketKey implements Serializable {

		private static final long serialVersionUID = 1L;

		@Column(name = "creator_id", nullable = false)
		private Integer creatorId;

		@Column(name = "schedule_id", nullable = false)
		private Integer scheduleId;

		public TicketKey() {
		}

		public Integer getCreatorId() {
			return creatorId;
		}

		public void setCreatorId(Integer CreatorId) {
			this.creatorId = CreatorId;
		}

		public int getScheduleId() {
			return scheduleId;
		}

		public void setScheduleId(Integer scheduleId) {
			this.scheduleId = scheduleId;
		}

	}
}
