package com.movie.movie.dtos;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.movie.movie.models.Film;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class FilmScheduleDTO {
    @JsonProperty("film_id")
    @Min(value = 1, message = "Film's ID must be > 0")
    private Film film;

    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @Column(name = "time_slot", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime timeSlot;
}
