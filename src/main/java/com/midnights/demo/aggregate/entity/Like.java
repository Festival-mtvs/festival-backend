package com.midnights.demo.aggregate.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("좋아요 번호")
    @Column(name = "like_no")
    private Long likeNo;

    @Column(name = "hold_time")
    @Comment("멤버 번호")
    private Long holdTime;

    @Column(name = "is_liked")
    @Comment("좋아요 상태")
    private Boolean isLiked;
}
