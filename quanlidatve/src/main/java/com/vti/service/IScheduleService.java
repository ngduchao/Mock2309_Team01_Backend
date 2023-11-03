package com.vti.service;

import com.vti.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
public interface IScheduleService {

    Page<Schedule> getSchedule(Pageable pageable,Integer movieId);

    void create(Schedule schedule);

    void deleteById(Integer id);
}
