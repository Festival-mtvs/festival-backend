package com.midnights.demo.service;

import com.midnights.demo.aggregate.dto.member.RequestRegisterMember;
import com.midnights.demo.aggregate.dto.member.ResponseRegisterMember;
import com.midnights.demo.aggregate.entity.Member;
import com.midnights.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public ResponseRegisterMember registMember(RequestRegisterMember requestRegisterMember) {
        Member member = Member.toEntity(requestRegisterMember);

        memberRepository.save(member);

        ResponseRegisterMember responseRegisterMember = ResponseRegisterMember.fromEntity(member);

        return responseRegisterMember;
    }

    @Transactional(readOnly = true)
    public boolean checkId(String memberId) {
        Member member = memberRepository.findById(memberId);

        if (member == null){
            return false;
        }
        return true;
    }
}
