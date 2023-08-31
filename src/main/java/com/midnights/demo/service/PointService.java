package com.midnights.demo.service;

import com.midnights.demo.aggregate.dto.point.RequestIncrePoint;
import com.midnights.demo.aggregate.dto.point.ResponseIncrePoint;
import com.midnights.demo.aggregate.dto.point.ResponseRankPoint;
import com.midnights.demo.aggregate.entity.Point;
import com.midnights.demo.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;

    @Transactional
    public ResponseIncrePoint increPoint(RequestIncrePoint requestIncrePoint) {
        Point point = pointRepository.findByMemberId(requestIncrePoint.getId());

        point.increasePoint(requestIncrePoint);

        ResponseIncrePoint responseIncrePoint = ResponseIncrePoint.fromEntity(point);

        return responseIncrePoint;
    }

    @Transactional(readOnly = true)
    public ResponseRankPoint rankPoint(Pageable pageable) {
        Sort descendingSort = Sort.by(Sort.Direction.DESC, "point");
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), descendingSort);

        Page<Point> points = pointRepository.findAll(sortedPageable);

        ResponseRankPoint responseRankPoint = ResponseRankPoint.fromEntity(points);

        return responseRankPoint;
    }
}
