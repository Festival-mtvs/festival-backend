package com.midnights.demo.aggregate.entity;

import com.midnights.demo.aggregate.dto.like.RequestPostLike;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("좋아요 번호")
    @Column(name = "like_no")
    private Long likeNo;

    @Column(name = "member_id")
    @Comment("멤버 아이디")
    private String memberId;

    @Column(name = "festival_no")
    @Comment("축제 번호")
    private Long festivalNo;

    @Column(name = "is_liked")
    @Comment("좋아요 상태")
    private Boolean isLiked;

    public static Heart toEntity(RequestPostLike requestPostLike, Long festivalNo) {
        return Heart.builder()
                .memberId(requestPostLike.getId())
                .festivalNo(festivalNo)
                .isLiked(true)
                .build();
    }

    public void changeLike() {
        this.isLiked = true;
    }

    public void changeDonLike() {
        this.isLiked = false;
    }
}
