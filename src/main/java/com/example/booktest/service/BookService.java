package com.example.booktest.service;

import com.example.booktest.dto.BookMypriceRequestDto;
import com.example.booktest.dto.BookRequestDto;
import com.example.booktest.dto.ItemDto;
import com.example.booktest.models.Book;
import com.example.booktest.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service // 서비스임을 선언합니다.
public class BookService {

    private final BookRepository bookRepository;
    private static final int MIN_Price = 100;

    public List<Book> createBook(Long userId) {
        return bookRepository.findAllByUserId(userId);
    }
    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Book createBook(BookRequestDto requestDto, Long userId ) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Book book = new Book(requestDto, userId);
        bookRepository.save(book);
        return book;
    }

    public Long updateBySearch(Long id, ItemDto itemDto) {
      Book book = bookRepository.findById(id).orElseThrow(
              ()  -> new IllegalArgumentException("아이디가 존재하지 않습니다")
      );
        book.updateByItemDto(itemDto);
        return id;
    }
    @Transactional
    public  Long update(Long id, BookMypriceRequestDto requestDto) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디 없다")
        );
        book.update(requestDto);
        return id;
    }
    // 모든 상품 조회 (관리자용)
    public List<Book> getAllProducts() {
        return bookRepository.findAll();
    }

}