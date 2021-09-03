package com.example.booktest.controller;

import com.example.booktest.dto.BookMypriceRequestDto;
import com.example.booktest.dto.BookRequestDto;
import com.example.booktest.models.*;
import com.example.booktest.repository.BookRepository;
import com.example.booktest.security.UserDetailsImpl;
import com.example.booktest.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class BookRestController {

    private final BookRepository bookRepository;
    private final BookService bookService;

    // 등록된 전체 상품 목록 조회
    @GetMapping("/api/books")
    public List<Book> createBook(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return bookService.createBook(userId);
    }

    @PostMapping("/api/books")
    public Book createBook(@RequestBody BookRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long userId = userDetails.getUser().getId();

        Book book = bookService.createBook(requestDto, userId);
        return book;
    }

    // 설정 가격 변경
    @PutMapping("/api/books/{id}")
    public Long updateBook(@PathVariable Long id, @RequestBody BookMypriceRequestDto requestDto) {
        return bookService.update(id, requestDto);

    }

    // (관리자용) 등록된 모든 상품 목록 조회
    @Secured("ROLE_ADMIN")
    @GetMapping("/api/admin/books")
    public List<Book> getAllProducts() {
        return bookService.getAllProducts();
    }

}




