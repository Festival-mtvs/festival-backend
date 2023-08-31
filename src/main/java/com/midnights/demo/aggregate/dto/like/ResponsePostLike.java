package com.midnights.demo.aggregate.dto.like;

import com.midnights.demo.aggregate.entity.Festival;
import com.midnights.demo.aggregate.entity.Heart;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponsePostLike {
    private Long festivalNo;

    private Long likeCount;

    public static ResponsePostLike fromEntity(Heart heart, Festival festival) {
        return ResponsePostLike.builder()
                .festivalNo(heart.getFestivalNo())
                .likeCount(festival.getLikeCount())
                .build();
    }
}
