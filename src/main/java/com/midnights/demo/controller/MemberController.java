package com.midnights.demo.controller;

import com.midnights.demo.aggregate.dto.member.RequestLoginMember;
import com.midnights.demo.aggregate.dto.member.RequestRegisterMember;
import com.midnights.demo.aggregate.dto.member.ResponseLoginMember;
import com.midnights.demo.aggregate.dto.member.ResponseRegisterMember;
import com.midnights.demo.exceptionhanlder.InvalidPasswordException;
import com.midnights.demo.exceptionhanlder.UserNotFoundException;
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

    /* 회원가입 */
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<?> register(@RequestBody @Valid RequestRegisterMember requestRegisterMember, Errors errors){

        // 아이디 및 비밀번호 공백 및 비밀번호 유효성 체크
        if(errors.hasErrors()){
            Map<String, String> validatorResult = new HashMap<>();

            for (FieldError error : errors.getFieldErrors()) {
                String validKeyName = String.format("error", error.getField());
                validatorResult.put(validKeyName, error.getDefaultMessage());
            }

            // 유효성에 걸리면 응답 반환
            return new ResponseEntity<>(validatorResult, HttpStatus.BAD_REQUEST);
        }
        ResponseRegisterMember responseRegisterMember = memberService.registMember(requestRegisterMember);

        // 정상적일 경우 200 반환하며 성공
        return ResponseEntity.ok(responseRegisterMember);
    }

    /* 아이디 중복 체크 */
    @PostMapping("/registerId")
    @ResponseBody
    public Map<String, String> check(@RequestBody RequestLoginMember requestLoginMember){
        Map<String, String> checkId = new HashMap<>();

        // 중복 체크
        if(!memberService.checkId(requestLoginMember.getId())) {
            checkId.put("message", "사용 가능한 아이디입니다.");
            return checkId;
        }

        checkId.put("message", "중복된 아이디입니다.");
        return checkId;
    }

    /* 로그인 컨트롤러 */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody RequestLoginMember requestLoginMember){
        try{
            ResponseLoginMember responseLoginMember = memberService.loginMember(requestLoginMember);
            return ResponseEntity.ok(responseLoginMember); // 로그인 성공 - 200 OK 상태 코드와 함께 응답 반환
        }catch(UserNotFoundException e){
            Map<String, String> userIdErrorResponse = new HashMap<>();
            userIdErrorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(userIdErrorResponse, HttpStatus.BAD_REQUEST); // 아이디 오류 - 400 Bad Request 상태 코드 반환

        }catch(InvalidPasswordException e){
            Map<String, String> passwordErrorResponse = new HashMap<>();
            passwordErrorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(passwordErrorResponse, HttpStatus.BAD_REQUEST); // 비밀번호 오류 - 400 Bad Request 상태 코드 반환
        }
    }
}
