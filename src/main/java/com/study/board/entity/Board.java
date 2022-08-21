package com.study.board.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema="board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String title;

    public String content;

    public String category;

    public String genderdisplay;

    public String date;
    public String noon;
    public String hour;
    public String minute;

    public String placename;
    public String position;

    public Integer currentpeople;
    public Integer maxpeople;
}

