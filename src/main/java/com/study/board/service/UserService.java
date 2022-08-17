package com.study.board.service;

import com.study.board.entity2.User;
import com.study.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void write(User user){

        userRepository.save(user);
    }
}
