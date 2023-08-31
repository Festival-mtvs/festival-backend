package com.midnights.demo.aggregate.entity;

import com.midnights.demo.aggregate.dto.member.RequestRegisterMember;
import com.midnights.demo.aggregate.dto.point.RequestIncrePoint;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("포인트 번호")
    @Column(name = "point_no")
    private Long pointNo;

    @Column(name = "point_count")
    @Comment("총 포인트")
    private Long point;

    @Column(name = "member_id")
    @Comment("회원 아이디")
    private String memberId;

    public static Point toEntity(RequestRegisterMember requestRegisterMember) {
        return Point.builder()
                .point(0L)
                .memberId(requestRegisterMember.getId())
                .build();
    }

    /* 포인트 증가 */
    public void increasePoint(RequestIncrePoint point) {
        this.point += point.getPoint();
    }
}
