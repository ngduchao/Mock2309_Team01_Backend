package com.vti.service;
import java.util.stream.Stream;
import com.vti.dto.ScheduleDto;
import com.vti.entity.Schedule;
import com.vti.repository.IScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ScheduleService implements IScheduleService {
    @Autowired
    private IScheduleRepository repository;
    @Override
    @Transactional
    public Page<Schedule> getSchedule(Pageable pageable, Integer movieId){
            return repository.getScheduleByMovieId(pageable,movieId);
    }
    @Override
    public void create(Schedule schedule){
        repository.save(schedule);
    }
    @Override
    public void deleteById(Integer id){
        repository.deleteById(id);
    }
}
