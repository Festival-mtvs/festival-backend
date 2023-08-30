package com.midnights.demo.aggregate.dto.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RequestRegisterMember {

    @NotBlank(message = "아이디는 빈 값일 수 없습니다.")
    private String id;

    @NotBlank(message = "비밀번호는 빈 값일 수 없습니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;
}
