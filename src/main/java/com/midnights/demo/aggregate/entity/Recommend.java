package com.midnights.demo.aggregate.entity;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Recommend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("추천 번호")
    @Column(name = "recommend_no")
    private Long recommendNo;

    @Column(name = "recommend_list")
    @Comment("추천 리스트")
    private String recommendedList;

    public static Recommend fromResponse(String response) {
        return Recommend.builder()
                .recommendedList(response)
                .build();
    }
}
