package com.study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLOutput;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

@Controller
public class UserController {

    @GetMapping("/board/login")
    @ResponseBody
    public String userLogin(){

        return "123123";
    }
}
