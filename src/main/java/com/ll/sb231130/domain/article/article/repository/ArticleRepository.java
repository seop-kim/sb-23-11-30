package com.ll.sb231130.domain.article.article.repository;

import com.ll.sb231130.domain.article.article.entity.Article;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByIdDesc();
}
