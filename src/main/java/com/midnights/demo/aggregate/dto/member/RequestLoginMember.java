package com.midnights.demo.aggregate.dto.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestLoginMember {
    private String id;
    private String password;

    public RequestLoginMember(String id) {
        this.id = id;
    }
}
