package com.midnights.demo.aggregate.dto.point;

import com.midnights.demo.aggregate.entity.Point;
import com.midnights.demo.common.ResponseMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseIncrePoint {
    private Long point;

    private String message;

    public static ResponseIncrePoint fromEntity(Point point) {
        return ResponseIncrePoint.builder()
                .point(point.getPoint())
                .message("points increased.")
                .build();
    }
}
