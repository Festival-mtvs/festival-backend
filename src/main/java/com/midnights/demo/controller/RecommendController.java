package com.midnights.demo.controller;


import com.midnights.demo.aggregate.dto.recommend.RequestRecommendList;
import com.midnights.demo.aggregate.dto.recommend.ResponseRecommendList;
import com.midnights.demo.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    /* 추천 로직 */
    @GetMapping("/recommendLike")
    @ResponseBody
    public ResponseEntity<ResponseRecommendList> recommend(@RequestBody RequestRecommendList requestRecommendList){
        ResponseRecommendList recommendList = recommendService.postRecommendList(requestRecommendList);
        return ResponseEntity.ok(recommendList);
    }
}
