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

    public Integer currentPeople;
    public Integer maxPeople;

    public String title;

    public String content;

    public String category;

    public String genderDisplay;

    public String date;
    public String noon;
    public Integer hour;
    public Integer minute;

    public String placeName;
    public String position;
}


