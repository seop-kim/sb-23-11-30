package com.ll.sb231130.domain.article.article.controller;

import com.ll.sb231130.domain.article.article.dto.ArticleDto;
import com.ll.sb231130.domain.article.article.entity.Article;
import com.ll.sb231130.domain.article.article.service.ArticleService;
import com.ll.sb231130.domain.member.member.entity.Member;
import com.ll.sb231130.global.rq.Rq;
import com.ll.sb231130.global.rsData.RsData;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ApiV1ArticleController {
    private final ArticleService articleService;
    private final Rq rq;

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

    @Getter
    public static class GetArticleResponseBody {
        private final ArticleDto item;

        public GetArticleResponseBody(Article article) {
            item = new ArticleDto(article);
        }
    }

    @GetMapping("/{id}")
    public RsData<GetArticleResponseBody> getArticle(
            @PathVariable long id
    ) {
        return RsData.of(
                "200",
                "성공",
                new GetArticleResponseBody(
                        articleService.findById(id).get()
                )
        );
    }

    @GetMapping("")
    public RsData<getArticlesResponseBody> getArticles() {
        return RsData.of("200", "성공", new getArticlesResponseBody(articleService.findAllByOrderByIdDesc()));
    }

    @Getter
    public static class RemoveArticleResponseBody {
        private final ArticleDto item;

        public RemoveArticleResponseBody(Article article) {
            item = new ArticleDto(article);
        }
    }

    @DeleteMapping("/{id}")
    public RsData<RemoveArticleResponseBody> removeArticle(
            @PathVariable long id
    ) {
        Article article = articleService.findById(id).get();

        articleService.deleteById(id);

        return RsData.of(
                "200",
                "성공",
                new RemoveArticleResponseBody(
                        article
                )
        );
    }

    @Getter
    @Setter
    public static class ModifyArticleRequestBody {
        private String title;
        private String body;
    }

    @Getter
    public static class ModifyArticleResponseBody {
        private final ArticleDto item;

        public ModifyArticleResponseBody(Article article) {
            item = new ArticleDto(article);
        }
    }

    @PutMapping("/{id}")
    public RsData<ModifyArticleResponseBody> modifyArticle(
            @PathVariable long id,
            @RequestBody ModifyArticleRequestBody body
    ) {
        Article article = articleService.findById(id).get();

        articleService.modify(article, body.getTitle(), body.getBody());

        return RsData.of(
                "200",
                "성공",
                new ModifyArticleResponseBody(
                        article
                )
        );
    }

    @Getter
    @Setter
    public static class WriteArticleRequestBody {
        private String title;
        private String body;
    }

    @Getter
    public static class WriteArticleResponseBody {
        private final ArticleDto item;

        public WriteArticleResponseBody(Article article) {
            item = new ArticleDto(article);
        }
    }

    @PostMapping("")
    public RsData<WriteArticleResponseBody> writeArticle(
            @RequestBody WriteArticleRequestBody body,
            Principal principal
    ) {
        Member member = rq.getMember();

        Optional.ofNullable(principal)
                .ifPresentOrElse(
                        p -> System.out.println("로그인 : " + p.getName()),
                        () -> System.out.println("비로그인"));


        RsData<Article> writeRs = articleService.write(member, body.getTitle(), body.getBody());

        return writeRs.of(
                new WriteArticleResponseBody(
                        writeRs.getData()
                )
        );
    }
}
