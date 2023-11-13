package com.movie.movie.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "film")
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Integer id;

    @Column(name = "name", length = 100, unique = true, nullable = false)
    private String name;

    @Column(name = "directors", nullable = false, length = 50)
    @Size(min = 6, max = 50)
    private String directors;

    @Column(name = "actors", nullable = false, length = 200)
    @Size(min = 6, max = 200)
    private String actors;

    @Column(name = "genre", nullable = false, length = 100)
    private String genre;

    @Column(name = "duration", nullable = false, length = 30)
    private String duration;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "release_date", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime releaseDate;

    @Column(name = "ticket_price", nullable = false)
    private Integer ticketPrice;

    @Column(name = "poster", nullable = false)
    private String poster;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;
}