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
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("포인트 번호")
    @Column(name = "point_no")
    private Long pointNo;

    @Column(name = "point_count")
    @Comment("총 포인트")
    private Long pointCount;

    @Column(name = "member_no")
    @Comment("회원 번호")
    private Long memberNo;
}
