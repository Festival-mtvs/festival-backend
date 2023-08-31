package com.midnights.demo.service;

import com.midnights.demo.aggregate.dto.point.RequestIncrePoint;
import com.midnights.demo.aggregate.dto.point.ResponseIncrePoint;
import com.midnights.demo.aggregate.entity.Point;
import com.midnights.demo.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
