package com.study.board.service;

import com.study.board.entity.Comment;
import com.study.board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public void write(Comment comment){

        commentRepository.save(comment);
    }
}
