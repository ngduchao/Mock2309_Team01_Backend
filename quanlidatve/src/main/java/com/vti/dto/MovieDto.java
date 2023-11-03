package com.vti.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MovieDto {
    private Integer Id;
    private String name;
    private String director;
    private String actor;
    private String type;
    private String time;
    private  String description;
    private Date premiereDate;
    private Integer price;
    private  String poster;

}
