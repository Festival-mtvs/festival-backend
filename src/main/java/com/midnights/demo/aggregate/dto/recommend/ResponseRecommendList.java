package com.midnights.demo.aggregate.dto.recommend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class ResponseRecommendList {

    private List<Long> recommendList;

    private String message;

    public static ResponseRecommendList fromResponse(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // JSON 응답을 List<Long> 형태로 파싱합니다.
            List<Long> recommendList = objectMapper.readValue(response, new TypeReference<List<Long>>() {});

            // ResponseRecommendList 객체를 생성하고 recommendList 필드를 설정합니다.
            return ResponseRecommendList.builder()
                    .recommendList(recommendList)
                    .message("추천 완료")
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null; // 파싱 실패 시 null 반환 또는 예외 처리를 고려할 수 있습니다.
        }
    }
}
