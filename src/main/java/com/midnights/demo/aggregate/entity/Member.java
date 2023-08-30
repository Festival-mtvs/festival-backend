package com.midnights.demo.aggregate.entity;

import com.midnights.demo.aggregate.dto.member.RequestRegisterMember;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회원 번호")
    @Column(name = "member_no")
    private Long memberNo;

    @Column(name = "id")
    @Comment("회원 아이디")
    private String id;

    @Column(name = "password")
    @Comment("회원 비밀번호")
    private String password;

    public static Member toEntity(RequestRegisterMember requestRegisterMember) {
        return Member.builder()
                .id(requestRegisterMember.getId())
                .password(requestRegisterMember.getPassword())
                .build();
    }
}
