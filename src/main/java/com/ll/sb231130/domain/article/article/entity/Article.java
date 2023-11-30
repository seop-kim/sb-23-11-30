package com.ll.sb231130.domain.article.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.sb231130.domain.member.member.entity.Member;
import com.ll.sb231130.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;

    private String title;

    private String body;
}