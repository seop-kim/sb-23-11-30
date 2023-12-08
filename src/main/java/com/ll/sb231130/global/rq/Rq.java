package com.ll.sb231130.global.rq;

import com.ll.sb231130.domain.member.member.entity.Member;
import com.ll.sb231130.domain.member.member.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final MemberService memberService;
    private final EntityManager entityManager;
    private Member member;

    public Member getMember() {
        if (member == null) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            long memberId = Long.parseLong(user.getUsername());

            // new Member(memberId)랑 같다. id만 존재하는 Member를 생성한 것이다.
            // id 값 밖에 없지만 만약 username 을 요청하게 되면 이때 sql이 생성되어 id를 통해 쿼리를 생성하여 username을 가져온다.
            member = entityManager.getReference(Member.class, memberId);
        }

        return member;
    }

    public String getHeader(String name, String defaultValue) {
        String value = request.getHeader(name);

        if (value == null) {
            return defaultValue;
        }

        return value;
    }
}

// facade