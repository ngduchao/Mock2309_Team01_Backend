package com.vti.repository;

import com.vti.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.format.DateTimeFormatter;
import java.util.List;

public interface IScheduleRepository extends JpaRepository<Schedule,Integer> , JpaSpecificationExecutor<Schedule> {
//    @Query("SELECT * FROM `Lich_Chieu_Phim` s WHERE s.`phim_id`=:movieId")
    Page<Schedule> getScheduleByMovieId(Pageable pageable, Integer movieId);
}
