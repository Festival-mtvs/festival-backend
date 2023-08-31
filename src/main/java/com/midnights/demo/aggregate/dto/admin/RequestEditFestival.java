package com.midnights.demo.aggregate.dto.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEditFestival {
    private Long festivalNo;
    private String festivalName;
    private String regionName;
    private String hostRegion;
    private String hostPeriod;
    private String festivalType;
    private String festivalPlace;
    private String placeType;
}
