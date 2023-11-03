package com.vti.controller;

import com.vti.dto.ScheduleDto;
import com.vti.entity.Schedule;
import com.vti.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/schedule")
public class ScheduleController {
    @Autowired
    private IScheduleService service;
    @Autowired
    private ModelMapper mapper;
    @GetMapping
    public Page<ScheduleDto> getSchedule(Pageable pageable,@PathVariable Integer movieId){
        Page<Schedule> schedules = service.getSchedule(pageable,movieId);
        return schedules.map(schedule -> {
            return mapper.map(schedule, ScheduleDto.class);
        });
    }
}
