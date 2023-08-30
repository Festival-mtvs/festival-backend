package com.midnights.demo.aggregate.entity;

import lombok.Getter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Table
public class Festival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("축제 번호")
    @Column(name = "festival_no")
    private Long festivalNo;

    @Comment("개최 지역")
    @Column(name = "host_area")
    private String hostArea;

    @Comment("개최 기간")
    @Column(name = "festival_period")
    private String festivalPeriod;

    @Comment("축제 유형")
    @Column(name = "festival_type")
    private String festivalType;

    @Comment("축제 장소")
    @Column(name = "festival_area")
    private String festivalArea;

    @Comment("장소 유형")
    @Column(name = "area_type")
    private String areaType;
}
