package com.movie.movie.dtos;

import java.time.LocalDateTime;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class FilmDTO {

    @JsonProperty("name")
    @NotBlank
    @Size(max = 100)
    private String name;

    @JsonProperty("directors")
    @NotBlank
    @Size(min = 6, max = 50)
    private String directors;

    @JsonProperty("actors")
    @NotBlank
    @Size(max = 200)
    private String actors;

    @JsonProperty("genre")
    @NotBlank
    @Size(max = 100)
    private String genre;

    @JsonProperty("duration")
    @NotBlank
    @Size(max = 30)
    private String duration;

    @JsonProperty("description")
    @NotBlank
    private String description;

    @JsonProperty("release_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime releaseDate;

    @JsonProperty("ticket_price")
    @NotBlank
    private Integer ticketPrice;

    @JsonProperty("poster")
    @NotBlank
    private String poster;

    @JsonProperty("creator_id")
    @Min(value = 1, message = "User's ID must be > 0")
    private Long creatorId;
}