package com.midnights.demo.aggregate.dto.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseLoginMember {
    private String id;

    private String message;
}
