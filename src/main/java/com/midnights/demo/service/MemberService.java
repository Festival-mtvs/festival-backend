package com.midnights.demo.service;

import com.midnights.demo.aggregate.dto.member.*;
import com.midnights.demo.aggregate.entity.Member;
import com.midnights.demo.aggregate.entity.Point;
import com.midnights.demo.exceptionhanlder.InvalidPasswordException;
import com.midnights.demo.exceptionhanlder.UserNotFoundException;
import com.midnights.demo.repository.MemberRepository;
import com.midnights.demo.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PointRepository pointRepository;

    /* 회원가입 */
    @Transactional
    public ResponseRegisterMember registMember(RequestRegisterMember requestRegisterMember) {
        Member member = Member.toEntity(requestRegisterMember);
        Point point = Point.toEntity(requestRegisterMember);

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


    /* 로그아웃 */
    @Transactional(readOnly = true)
    public boolean logoutMember(HttpSession session, String memberNo) {
        if (session != null) {
            String sessionId = (String) session.getAttribute("memberId");

            // 세션에 저장된 memberId와 요청으로 들어온 memberId가 일치하는지 확인
            // 일치하면 로그아웃 처리
            if (sessionId != null && sessionId.equals(memberNo)) {
                session.invalidate();

                return true;
            }
        }

        return false;
    }
}
