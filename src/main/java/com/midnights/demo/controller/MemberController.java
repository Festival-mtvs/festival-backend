package com.midnights.demo.controller;

import com.midnights.demo.aggregate.dto.member.RequestRegisterMember;
import com.midnights.demo.aggregate.dto.member.ResponseRegisterMember;
import com.midnights.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("api/v1/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody @Valid RequestRegisterMember requestRegisterMember, Errors errors){
        if(errors.hasErrors()){
            Map<String, String> validatorResult = new HashMap<>();

            for (FieldError error : errors.getFieldErrors()) {
                String validKeyName = String.format("valid_%s", error.getField());
                validatorResult.put(validKeyName, error.getDefaultMessage());
            }
            return new ResponseEntity<>(validatorResult, HttpStatus.BAD_REQUEST);
        }

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
