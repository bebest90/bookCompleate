package com.example.booktest.controller;


import com.example.booktest.dto.AritcleRequestDto;
import com.example.booktest.models.Article;
import com.example.booktest.repository.ArticleRepository;
import com.example.booktest.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController()
public class ArticleRestController {
    private final ArticleService articleService;
    private final ArticleRepository articleRepository;

    @GetMapping("/api/articles")
    public List<Article> getArticle() {
        return articleRepository.findAllByOrderByModifiedAtDesc();
    }



    @GetMapping("/api/detail/{id}")
    public Article getDetail(@PathVariable Long id) {
        return articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("null"));
    }

    @PostMapping("/api/articles")
    public Article createArticle(@RequestBody AritcleRequestDto aritcleRequestDto) {
        Article article = new Article(aritcleRequestDto);
        return articleRepository.save(article);
    }

    @PutMapping("/api/articles/{id}")
    public Long updateArticle(@PathVariable Long id, @RequestBody AritcleRequestDto aritcleRequestDto) {
        return articleService.update(id, aritcleRequestDto);
    }

    @DeleteMapping("/api/articles/{id}")
    public Long deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return id;
    }


}
