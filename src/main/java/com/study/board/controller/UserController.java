package com.study.board.controller;

import com.study.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLOutput;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/board/signin")
    public String userSignin(){

        return "usersignin";
    }

    @GetMapping("/board/signup")
    public String userSignup(){

        return "usersignup";
    }
}
