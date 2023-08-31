package com.midnights.demo.aggregate.entity;

import com.midnights.demo.aggregate.dto.admin.RequestCreateFestival;
import com.midnights.demo.aggregate.dto.admin.RequestEditFestival;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Table
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Festival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("축제 번호")
    @Column(name = "festival_no")
    private Long festivalNo;

    @Comment("축제 명")
    @Column(name = "festival_name")
    private String festivalName;

    @Comment("지역 명")
    @Column(name = "city_name")
    private String cityName;

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

    @Comment("좋아요 수")
    @Column(name = "like_count")
    private Long likeCount;

    @Comment("is_homepage")
    @Column(name = "is_homepage")
    private int isHomepage;


    public static Festival toEntity(RequestCreateFestival requestCreateFestival) {
        return Festival.builder()
                .festivalName(requestCreateFestival.getFestivalName())
                .cityName(requestCreateFestival.getRegionName())
                .hostArea(requestCreateFestival.getHostRegion())
                .festivalPeriod(requestCreateFestival.getHostPeriod())
                .festivalType(requestCreateFestival.getFestivalType())
                .festivalArea(requestCreateFestival.getFestivalPlace())
                .areaType(requestCreateFestival.getPlaceType())
                .likeCount(0L)
                .isHomepage(0)
                .build();
    }

    public void increaseLikeCount() {
        this.likeCount += 1L;
    }

    public void decreaseLikeCount() {
        this.likeCount = Math.max(0L, this.likeCount - 1);
    }

    public void update(RequestEditFestival requestEditFestival) {
        this.festivalName = requestEditFestival.getFestivalName();
        this.cityName = requestEditFestival.getRegionName();
        this.hostArea = requestEditFestival.getHostRegion();
        this.festivalPeriod = requestEditFestival.getHostPeriod();
        this.festivalType = requestEditFestival.getFestivalType();
        this.festivalArea = requestEditFestival.getFestivalPlace();
        this.areaType = requestEditFestival.getPlaceType();
    }
}
