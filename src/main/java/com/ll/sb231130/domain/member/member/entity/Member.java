package com.ll.sb231130.domain.member.member.entity;

import com.ll.sb231130.global.jpa.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    private String username;
    private String password;
    private String email;
    private String nickname;

    public String getName() {
        return nickname;
    }

    @SuppressWarnings("JpaAttributeTypeInspection")
    public List<? extends GrantedAuthority> getAuthorities() {
        return getAuthoritiesAsStrList()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @SuppressWarnings("JpaAttributeTypeInspection")
    public List<String> getAuthoritiesAsStrList() {
        return List.of("ROLE_MEMBER");
    }
}
