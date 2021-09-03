package com.example.booktest.utils;


import com.example.booktest.models.Comment;
import org.springframework.data.jpa.domain.Specification;

public class CommentSpecs {

    public static Specification<Comment> withArticle_id(Long article_id) {
        return ((root, query, builder) ->
                builder.equal(root.get("article_id"), article_id)
        );
    }
}
