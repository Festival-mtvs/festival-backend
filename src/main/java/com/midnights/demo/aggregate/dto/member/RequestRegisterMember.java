package com.midnights.demo.aggregate.dto.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
public class RequestRegisterMember {

    @NotBlank(message = "ID is not Blank!!")
    private String id;

    @NotBlank(message = "Password is not Blank!!!")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "Posts should be 8 to 16 characters long, using English letters, numbers, numbers, and letters.")
    private String password;
}
