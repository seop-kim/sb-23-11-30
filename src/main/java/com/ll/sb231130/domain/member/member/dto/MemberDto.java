package com.ll.sb231130.domain.member.member.dto;

import com.ll.sb231130.domain.member.member.entity.Member;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class MemberDto {
    private final Long id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final String username;
    private final String nickname;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.createDate = member.getCreateDate();
        this.modifyDate = member.getModifyDate();
        this.username = member.getUsername();
        this.nickname = member.getNickName();
    }
}