package com.midnights.demo.service;

import com.midnights.demo.aggregate.dto.member.RequestLoginMember;
import com.midnights.demo.aggregate.dto.member.RequestRegisterMember;
import com.midnights.demo.aggregate.dto.member.ResponseLoginMember;
import com.midnights.demo.aggregate.dto.member.ResponseRegisterMember;
import com.midnights.demo.aggregate.entity.Member;
import com.midnights.demo.exceptionhanlder.InvalidPasswordException;
import com.midnights.demo.exceptionhanlder.UserNotFoundException;
import com.midnights.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /* 회원가입 */
    @Transactional
    public ResponseRegisterMember registMember(RequestRegisterMember requestRegisterMember) {
        Member member = Member.toEntity(requestRegisterMember);

        memberRepository.save(member);

        ResponseRegisterMember responseRegisterMember = ResponseRegisterMember.fromEntity(member);

        return responseRegisterMember;
    }

    /* 아이디 중복 체크 */
    @Transactional(readOnly = true)
    public boolean checkId(String memberId) {
        Member member = memberRepository.findById(memberId);

        if (member == null){
            return false;
        }
        return true;
    }

    /* 로그인 */
    @Transactional(readOnly = true)
    public ResponseLoginMember loginMember(RequestLoginMember requestLoginMember) {
        Member member = memberRepository.findById(requestLoginMember.getId());

        if (member == null){
            throw new UserNotFoundException("아이디가 존재하지 않습니다.");
        }

        else if (!(member.getPassword().equals(requestLoginMember.getPassword()))){
            throw new InvalidPasswordException("비밀번호가 다릅니다.");
        }

        ResponseLoginMember responseLoginMember = ResponseLoginMember.fromEntity(member);

        return responseLoginMember;
    }
}
