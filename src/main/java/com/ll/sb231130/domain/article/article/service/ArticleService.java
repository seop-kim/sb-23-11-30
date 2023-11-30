package com.ll.sb231130.domain.article.article.service;

import com.ll.sb231130.domain.article.article.entity.Article;
import com.ll.sb231130.domain.article.article.repository.ArticleRepository;
import com.ll.sb231130.domain.member.member.entity.Member;
import com.ll.sb231130.global.rsData.RsData;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public RsData<Article> write(Member author, String title, String body) {
        Article article = Article.builder()
                .modifyDate(LocalDateTime.now())
                .author(author)
                .title(title)
                .body(body)
                .build();

        articleRepository.save(article);

        return RsData.of("200", "%d번 게시글 작성이 완료되었습니다.".formatted(article.getId()), article);
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }
}
