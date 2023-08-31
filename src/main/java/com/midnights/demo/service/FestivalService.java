package com.midnights.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.midnights.demo.aggregate.dto.festival.FestivalDTO;
import com.midnights.demo.aggregate.dto.like.RequestPostLike;
import com.midnights.demo.aggregate.dto.like.ResponsePostLike;
import com.midnights.demo.aggregate.entity.Festival;
import com.midnights.demo.aggregate.entity.Heart;
import com.midnights.demo.repository.FestivalRepository;
import com.midnights.demo.repository.HeartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FestivalService {

    private final FestivalRepository festivalRepository;
    private final HeartRepository heartRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
//    private String hostArea;

    @Autowired
    public FestivalService(FestivalRepository festivalRepository, ModelMapper modelMapper, ObjectMapper objectMapper, HeartRepository heartRepository) {
        this.festivalRepository = festivalRepository;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
        this.heartRepository = heartRepository;
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


    @Transactional
    /* 좋아요 수 증감 */
    public ResponsePostLike postLike(RequestPostLike requestPostLike, Long festivalNo) {
        Festival festival = festivalRepository.findFestivalByFestivalNo(festivalNo);
        Heart heart = heartRepository.findByMemberId(requestPostLike.getId());

        if(heart == null){
            Heart findHeart = Heart.toEntity(requestPostLike, festivalNo);
            heartRepository.save(findHeart);
            festival.increaseLikeCount();

            ResponsePostLike responsePostLike = ResponsePostLike.fromEntity(findHeart, festival);
            return responsePostLike;
        }

        if(heart.getIsLiked() == false){
            heart.changeLike();
            festival.increaseLikeCount();
        } else {
            heart.changeDonLike();
            festival.decreaseLikeCount();
        }

        ResponsePostLike responsePostLike = ResponsePostLike.fromEntity(heart, festival);
        return responsePostLike;
    }

    public Page<Festival> findAllFestivals(Pageable pageable) {

        Page<Festival> festivals = festivalRepository.findAll(pageable);
        return festivals;
    }
}
