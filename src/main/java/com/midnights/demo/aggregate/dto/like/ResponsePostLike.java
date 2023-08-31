package com.midnights.demo.aggregate.dto.like;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.midnights.demo.aggregate.dto.recommend.ResponseRecommendList;
import com.midnights.demo.aggregate.entity.Festival;
import com.midnights.demo.aggregate.entity.Heart;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponsePostLike {

    private List<JsonNode> recommendList;

    public static ResponsePostLike fromResponse(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // JSON 응답을 List<Long> 형태로 파싱하지 않고 그대로 List<JsonNode>으로 파싱합니다.
            List<JsonNode> jsonNodes = objectMapper.readValue(response, new TypeReference<List<JsonNode>>() {});

            // ResponsePostLike 객체를 생성하고 recommendList 필드에 jsonNodes를 설정합니다.
            return ResponsePostLike.builder()
                    .recommendList(jsonNodes)
                    .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null; // 파싱 실패 시 null 반환 또는 예외 처리를 고려할 수 있습니다.
        }
    }
}
