package com.vti.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "`Lich_Chieu_Phim`")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`lich_chieu_id`")
    private Integer id;
    @Column(name = "`so_luong_ghe`", length =100 )
    private Integer numberOfSeat;
    @DateTimeFormat
    @Column(name = "`ngay_chieu`",nullable = false)
    private Date showDate;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "`phim_id`",nullable = false)
    private Movie movie;
}
