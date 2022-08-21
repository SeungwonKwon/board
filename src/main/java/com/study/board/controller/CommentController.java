package com.study.board.controller;

import com.study.board.entity.Comment;
import com.study.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/api/comment")
    public String createPost(@RequestBody Comment comment, Model model) {

        commentService.write(comment);

        model.addAttribute("message", "댓글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/");

        return "message";
    }
}
