package com.midnights.demo.service;

import com.midnights.demo.aggregate.dto.recommend.RequestRecommendList;
import com.midnights.demo.aggregate.dto.recommend.ResponseRecommendList;
import com.midnights.demo.aggregate.entity.Heart;
import com.midnights.demo.repository.HeartRepository;
import com.midnights.demo.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final HeartRepository heartRepository;

    private final RecommendRepository recommendRepository;

    @Transactional(readOnly = true)
    public ResponseRecommendList postRecommendList(RequestRecommendList requestRecommendList) {
        List<Heart> hearts = heartRepository.findAllByMemberId(requestRecommendList.getId());
        System.out.println("hearts = " + hearts);

        return null;
    }
}
