package com.ll.sb231130.domain.article.article.dto;

import com.ll.sb231130.domain.article.article.entity.Article;
import com.ll.sb231130.domain.member.member.entity.Member;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;


@Getter
public class ArticleDto {
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private Long authorId;
    private final String authorName;
    private Member author;
    private String title;
    private String body;

    public ArticleDto(Article article) {
        this.id = article.getId();
        this.createDate = article.getCreateDate();
        this.modifyDate = article.getModifyDate();
        this.authorId = article.getAuthor().getId();
        this.authorName = article.getAuthor().getName();
        this.title = article.getTitle();
        this.body = article.getBody();
    }
}