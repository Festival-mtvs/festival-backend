package com.midnights.demo.controller;

import com.midnights.demo.aggregate.dto.member.RequestRegisterMember;
import com.midnights.demo.aggregate.dto.member.ResponseRegisterMember;
import com.midnights.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<ResponseRegisterMember> register(@RequestBody RequestRegisterMember requestRegisterMember){
        ResponseRegisterMember responseRegisterMember = memberService.registMember(requestRegisterMember);

        return ResponseEntity.ok(responseRegisterMember);
    }

    @PostMapping("/registerId")
    @ResponseBody
    public String check(String memberId){
        if(!(memberService.checkId(memberId))) {
            return "중복된 아이디입니다.";
        }
        return "사용 가능한 아이디입니다.";
    }
}
