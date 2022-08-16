package com.study.board.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public Integer number;

    public String title;

    public String content;

    public String category;

    public String gender;

    public String date;
    public String MA;
    public Integer hour;
    public Integer minute;

    public String place;
    public String locate;
}


