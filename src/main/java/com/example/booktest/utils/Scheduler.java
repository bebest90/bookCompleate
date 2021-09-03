package com.example.booktest.utils;

import com.example.booktest.models.Book;
import com.example.booktest.repository.BookRepository;
import com.example.booktest.service.BookService;
import com.example.booktest.dto.ItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor // final 멤버 변수를 자동으로 생성합니다.
@Component // 스프링이 필요 시 자동으로 생성하는 클래스 목록에 추가합니다.
public class Scheduler {

    private final BookRepository bookRepository;
    private final BookService BookService;
    private final NaverBookSearch naverBookSearch;

    // 초, 분, 시, 일, 월, 주 순서
    @Scheduled(cron = "0 0 1 * * *")
    public void updatePrice() throws InterruptedException {
        System.out.println("가격 업데이트 실행");
        // 저장된 모든 관심상품을 조회합니다.
        List<Book> bookList = bookRepository.findAll();
        for (int i=0; i<bookList.size(); i++) {
            // 1초에 한 상품 씩 조회합니다 (Naver 제한)
            TimeUnit.SECONDS.sleep(1);
            // i 번째 관심 상품을 꺼냅니다.
            Book b = bookList.get(i);
            // i 번째 관심 상품의 제목으로 검색을 실행합니다.
            String title = b.getTitle();
            String resultString = naverBookSearch.search(title);
            // i 번째 관심 상품의 검색 결과 목록 중에서 첫 번째 결과를 꺼냅니다.
            List<ItemDto> itemDtoList = naverBookSearch.fromJSONtoItems(resultString);
            ItemDto itemDto = itemDtoList.get(0);
            // i 번째 관심 상품 정보를 업데이트합니다.
            Long id = b.getId();
            BookService.updateBySearch(id, itemDto);
        }
    }
}