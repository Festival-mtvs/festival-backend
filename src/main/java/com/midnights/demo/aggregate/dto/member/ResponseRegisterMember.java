package com.midnights.demo.aggregate.dto.member;

import com.midnights.demo.aggregate.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseRegisterMember {

    private Long memberNo;

    private String message;
    public static ResponseRegisterMember fromEntity(Member member) {
        return ResponseRegisterMember.builder()
                .memberNo(member.getMemberNo())
                .message("register successfully")
                .build();
    }
}
