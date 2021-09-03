package com.example.booktest.service;


import com.example.booktest.dto.AritcleRequestDto;
import com.example.booktest.models.Article;
import com.example.booktest.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public Long update(Long id, AritcleRequestDto aritcleRequestDto){
        Article article = articleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디가 없습니다.")
        );
        article.update(aritcleRequestDto);
        return article.getId();
    }
}
