package com.vti.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "`Phim`")
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`phim_id`")
    private Integer Id;
    @Column(name = "`ten_phim`", nullable = false, length = 100, unique = true)
    private String name;
    @Column(name = "`dao_dien`", nullable = false, length = 50)

    private String director;
    @Column(name = "`dien_vien`", nullable = false, length = 200)

    private String actor;
    @Column(name = "`the_loai`", nullable = false, length = 100)

    private String type;
    @Column(name = "`thoi_luong`", nullable = false, length = 30)

    private String time;
    @Column(name = "`tom_tat`", nullable = false, length = 200)

    private  String description;
    @DateTimeFormat
    @Column(name = "`ngay_khoi_chieu`", nullable = false )

    private Date premiereDate;
    @Column(name = "`gia_ve`", nullable = false )

    private Integer price;
    @Column(name = "`poster`", nullable = false )
    private  String poster;
    @Column(name = "`creator_id`")
    private Integer creatorId;

}
