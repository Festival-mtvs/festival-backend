package com.midnights.demo.aggregate.dto.point;

import com.midnights.demo.aggregate.entity.Point;
import com.midnights.demo.common.ResponseMessage;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseRankPoint {

    private List<Point> results;
    private int currentPage;
    private int totalItems;
    private int totalPages;

    public static ResponseRankPoint fromEntity(Page<Point> pointsPage) {
        return ResponseRankPoint.builder()
                .results(pointsPage.getContent())
                .currentPage(pointsPage.getNumber())
                .totalItems((int)pointsPage.getTotalElements())
                .totalPages(pointsPage.getTotalPages())
                .build();
    }
}
