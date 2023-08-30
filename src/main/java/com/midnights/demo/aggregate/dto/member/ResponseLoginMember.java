package com.midnights.demo.aggregate.dto.member;

import com.midnights.demo.aggregate.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseLoginMember {
    private String id;

    private String message;

    public static ResponseLoginMember fromEntity(Member member) {
        return ResponseLoginMember.builder()
                .id(member.getId())
                .message("로그인 완료")
                .build();
    }
}
