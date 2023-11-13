package com.movie.movie.models;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Entity
@Table(name = "Film_Schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Film film;

    @Column(name = "seat_number", nullable = false)
    @Min(50)
    @Max(100)
    private Integer seatNumber;

    @Column(name = "time_slot", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime timeSlot;
}