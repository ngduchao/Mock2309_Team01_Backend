package com.vti.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ScheduleDto {
    private Integer Id;
    private Integer numberOfSeat;
    private Date premiereDate;
//    private String movieName;
    private MovieDto movieDto;
}
