package com.example.booktest.models;



import com.example.booktest.Timestamped;
import com.example.booktest.dto.AritcleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Setter
public class Article extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String contents;

    public Article(AritcleRequestDto aritcleRequestDto){
        this.username = aritcleRequestDto.getUsername();
        this.title = aritcleRequestDto.getTitle();
        this.contents = aritcleRequestDto.getContents();
    }

    public void update(AritcleRequestDto aritcleRequestDto){
        this.username = aritcleRequestDto.getUsername();
        this.title = aritcleRequestDto.getTitle();
        this.contents = aritcleRequestDto.getContents();
    }
}
