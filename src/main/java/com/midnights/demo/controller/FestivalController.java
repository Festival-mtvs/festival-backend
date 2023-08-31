package com.midnights.demo.controller;

import com.midnights.demo.aggregate.dto.festival.FestivalDTO;
import com.midnights.demo.aggregate.dto.like.RequestPostLike;
import com.midnights.demo.aggregate.dto.like.ResponsePostLike;
import com.midnights.demo.aggregate.entity.Festival;
import com.midnights.demo.common.ResponseMessage;
import com.midnights.demo.service.FestivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1")
public class FestivalController {

    private final FestivalService festivalService;

    @Autowired
    public FestivalController(FestivalService festivalService) {
        this.festivalService = festivalService;
    }

    // 모든 축제 조회
    @GetMapping("/festivals")
    public ResponseEntity<ResponseMessage> findFestivalByCityName(@PageableDefault(size = 3) Pageable pageable, @RequestParam String cityName) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> responseMap = new HashMap<>();

        Page<Festival> festivals = festivalService.findFestivalByCityName(pageable, cityName);

        responseMap.put("festivals", festivals);

        return new ResponseEntity<>(
                new ResponseMessage(200, "success", responseMap),
                headers,
                HttpStatus.OK
        );
    }

    // 축제 상세 조회
    @GetMapping("/festivals/{festivalNo}")
    public ResponseEntity<ResponseMessage> findFestivalByNo(@PathVariable Long festivalNo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        FestivalDTO findFestival = festivalService.findFestivalByNo(festivalNo);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("festivals", findFestival);

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "success", responseMap));
    }

    @PostMapping("/festivals/like")
    @ResponseBody
    public ResponseEntity<ResponsePostLike> postLike(@RequestBody RequestPostLike requestPostLike){
        ResponsePostLike responsePostLike = festivalService.postLike(requestPostLike);
        return responsePostLike;
    }

}
