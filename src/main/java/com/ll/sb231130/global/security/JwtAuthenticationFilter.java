package com.ll.sb231130.global.security;

import com.ll.sb231130.domain.member.member.entity.Member;
import com.ll.sb231130.domain.member.member.service.MemberService;
import com.ll.sb231130.global.rq.Rq;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final MemberService memberService;
    private final Rq rq;

    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        String apiKey = rq.getHeader("X-ApiKey","");

        if (apiKey != null) {
            User user = memberService.getUserFromApiKey(apiKey);

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user,
                    user.getPassword(),
                    user.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}