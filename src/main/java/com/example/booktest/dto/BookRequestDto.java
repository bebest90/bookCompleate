package com.example.booktest.dto;

import lombok.Getter;

@Getter
public class BookRequestDto {
    private String title;
    private String link;
    private String image;
    private int price;
}
