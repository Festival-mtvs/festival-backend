package com.midnights.demo.aggregate.dto.member;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ResponseLogoutMember {
    private String id;

    private String message;
}
