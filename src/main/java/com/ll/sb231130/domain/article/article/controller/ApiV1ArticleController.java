package com.ll.sb231130.domain.article.article.controller;

import com.ll.sb231130.domain.article.article.dto.ArticleDto;
import com.ll.sb231130.domain.article.article.entity.Article;
import com.ll.sb231130.domain.article.article.service.ArticleService;
import com.ll.sb231130.global.rsData.RsData;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ApiV1ArticleController {
    private final ArticleService articleService;

    @Getter
    public static class getArticlesResponseBody {
        private final List<ArticleDto> items;
        private final Map pagination;

        public getArticlesResponseBody(List<Article> articles) {
            this.items = articles
                    .stream()
                    .map(ArticleDto::new)
                    .toList();

            pagination = Map.of("page", 1);
        }
    }

    @GetMapping("")
    public RsData<getArticlesResponseBody> getArticles() {
        return RsData.of("200", "성공", new getArticlesResponseBody(articleService.findAllByOrderByIdDesc()));
    }
}
