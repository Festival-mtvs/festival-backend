package com.midnights.demo.controller;

import com.midnights.demo.aggregate.dto.festival.FestivalDTO;
import com.midnights.demo.common.ResponseMessage;
import com.midnights.demo.service.FestivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public ResponseEntity<ResponseMessage> findAllFestivalByHostArea(@PageableDefault(size = 3) Pageable pageable) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        Map<String, Object> responseMap = new HashMap<>();

        List<FestivalDTO> festivals = festivalService.findAllFestivalByHostArea(pageable);

        responseMap.put("festivals", festivals);

        return new ResponseEntity<>(
                new ResponseMessage(200, "조회성공", responseMap),
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
                .body(new ResponseMessage(200, "조회성공", responseMap));
    }

}
