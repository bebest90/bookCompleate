package com.example.booktest.service;

import com.example.booktest.dto.CommentReqeustDto;
import com.example.booktest.models.Comment;
import com.example.booktest.repository.CommentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public Long update_comment(Long id, CommentReqeustDto commentReqeustDto){
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디가 없습니다.")
        );
        comment.update_comment(commentReqeustDto);
        return comment.getId();

    }

}
