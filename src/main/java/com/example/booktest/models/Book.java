package com.example.booktest.models;

import com.example.booktest.Timestamped;
import com.example.booktest.dto.BookMypriceRequestDto;
import com.example.booktest.dto.BookRequestDto;
import com.example.booktest.dto.ItemDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Book extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int myprice;

    @Column(nullable = false)
    private Long userId;

    // 관심 상품 생성 시 이용합니다.
    public Book(BookRequestDto requestDto , Long userId) {
        // 관심상품을 등록한 회원 Id 저장
        this.userId = userId;
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.price = requestDto.getPrice();
        this.myprice = 0;
    }



    public void updateByItemDto(ItemDto itemDto) {
        this.price = itemDto.getPrice();
    }

    // 관심 가격 변경 시 이용합니다.
    public void update(BookMypriceRequestDto requestDto) {
        this.myprice = requestDto.getMyprice();
    }
}
