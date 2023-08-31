package com.midnights.demo.aggregate.dto.admin;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestCreateFestival {
    private String festivalName;
    private String regionName;
    private String hostRegion;
    private String hostPeriod;
    private String festivalType;
    private String festivalPlace;
    private String placeType;
}
