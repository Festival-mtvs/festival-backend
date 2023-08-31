package com.midnights.demo.service;

import com.midnights.demo.aggregate.dto.recommend.RequestRecommendList;
import com.midnights.demo.aggregate.dto.recommend.ResponseRecommendList;
import com.midnights.demo.aggregate.entity.Heart;
import com.midnights.demo.repository.HeartRepository;
import com.midnights.demo.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final HeartRepository heartRepository;

    private final RecommendRepository recommendRepository;

    @Transactional(readOnly = true)
    public ResponseRecommendList postRecommendList(RequestRecommendList requestRecommendList) {
        List<Heart> hearts = heartRepository.findAllByMemberId(requestRecommendList.getId());

        List<Long> festivalNos = new ArrayList<>();
        for (Heart heart : hearts) {
            festivalNos.add(heart.getFestivalNo());
        }

        String url = "https://3cbd-210-110-77-226.ngrok-free.app";

        WebClient webClient = WebClient.create();

        // festivalNos를 리스트 형태로 전송하기 위해 Map을 사용합니다.
        Map<String, Object> data = new HashMap<>();
        data.put("festival_no", festivalNos);

        // POST 요청을 보내고 응답을 받습니다.
        String response = webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(data))
                .retrieve()
                .bodyToMono(String.class)
                .block();

//        String response = "[1,2,3]";

        System.out.println("response = " + response);

        ResponseRecommendList recommendList = ResponseRecommendList.fromResponse(response);
        return recommendList;
    }
}
