package com.midnights.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.midnights.demo.aggregate.dto.festival.FestivalDTO;
import com.midnights.demo.aggregate.dto.like.RequestPostLike;
import com.midnights.demo.aggregate.dto.like.ResponsePostLike;
import com.midnights.demo.aggregate.entity.Festival;
import com.midnights.demo.aggregate.entity.Heart;
import com.midnights.demo.repository.FestivalRepository;
import com.midnights.demo.repository.HeartRepository;
import com.midnights.demo.repository.RecommendRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class FestivalService {

    private final FestivalRepository festivalRepository;
    private final HeartRepository heartRepository;
    private final RecommendRepository recommendRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public FestivalService(FestivalRepository festivalRepository, ModelMapper modelMapper, ObjectMapper objectMapper, HeartRepository heartRepository,
                           RecommendRepository recommendRepository) {
        this.festivalRepository = festivalRepository;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
        this.heartRepository = heartRepository;
        this.recommendRepository = recommendRepository;
    }


    // 해당 지역에 포함 된 festival List 조회
    public Page<Festival> findFestivalByCityName(Pageable pageable , String cityName ) {
        Page<Festival> festivals = festivalRepository.findFestivalByCityName(cityName, pageable);

        return festivals;
//        return festivals.stream().map(festival -> modelMapper.map(festival, FestivalDTO.class)).collect(Collectors.toList());
    }

    // 상세 조회
    public FestivalDTO findFestivalByNo(Long festivalNo) {
        Festival findFestival = festivalRepository.findFestivalByFestivalNo(festivalNo);

        return modelMapper.map(findFestival, FestivalDTO.class);
    }

    /* 좋아요 + 추천 */
    @Transactional
    public ResponsePostLike postLike(RequestPostLike requestPostLike, Long festivalNo) {
        Festival festival = festivalRepository.findFestivalByFestivalNo(festivalNo);
        Heart heart = heartRepository.findByMemberId(requestPostLike.getId());

        if(heart == null){
            Heart findHeart = Heart.toEntity(requestPostLike, festivalNo);
            heartRepository.save(findHeart);
            festival.increaseLikeCount();

            String response = sendToFlaskServer(festivalNo);
            ResponsePostLike responsePostLike = ResponsePostLike.fromResponse(response, festival.getLikeCount());

            return responsePostLike;
        }

        if(heart.getIsLiked() == false){
            heart.changeLike();
            festival.increaseLikeCount();

            String response = sendToFlaskServer(festivalNo);
            ResponsePostLike responsePostLike = ResponsePostLike.fromResponse(response, festival.getLikeCount());

            return responsePostLike;
        }
        else {
            heart.changeDonLike();
            festival.decreaseLikeCount();

            return ResponsePostLike.builder()
                    .recommendList(new ArrayList<>())
                    .likeCount(festival.getLikeCount())
                    .build();
        }
    }

    private String sendToFlaskServer(Long festivalNo) {
        String url = "https://53f9-210-110-77-226.ngrok-free.app";

        WebClient webClient = WebClient.create();

        Map<String, Object> data = new HashMap<>();
        data.put("festival_no", festivalNo);

        String response = webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(data))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("response = " + response);

        return response;
    }

    public Page<Festival> findAllFestivals(Pageable pageable) {

        Page<Festival> festivals = festivalRepository.findAll(pageable);
        return festivals;
    }
}
