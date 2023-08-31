package com.midnights.demo.controller;

import com.midnights.demo.aggregate.dto.point.RequestIncrePoint;
import com.midnights.demo.aggregate.dto.point.ResponseIncrePoint;
import com.midnights.demo.aggregate.dto.point.ResponseRankPoint;
import com.midnights.demo.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/point")
public class PointController {

    private final PointService pointService;


    /* 랭킹 조회 */
    @GetMapping("/ranking")
    @ResponseBody
    public ResponseEntity<ResponseRankPoint> ranking(Pageable pageable){
        ResponseRankPoint responseRankPoint = pointService.rankPoint(pageable);
        return ResponseEntity.ok(responseRankPoint);
    }

    /* 포인트 증가 */
    @PostMapping("/increase")
    @ResponseBody
    public ResponseEntity<ResponseIncrePoint> increase(@RequestBody RequestIncrePoint requestIncrePoint){
        ResponseIncrePoint responseIncrePoint = pointService.increPoint(requestIncrePoint);
        return ResponseEntity.ok(responseIncrePoint);
    }
}
