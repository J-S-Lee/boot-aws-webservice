package com.jslee.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity // 테이블과 링크될 클래스 (JPA annotation). 언더스코어 네이밍으로 테이블 이름 매칭(ex : SalesManager.java -> sales_manager table
public class Posts extends BaseTimeEntity {

    @Id // PK field
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙 스프링부트 2.0에선 GenerationType.IDENTITY 옵션을 추가해야 auto_increment 가능
    private Long id;

    @Column(length = 500, nullable = false) // 테이블 컬럼을 나타냄(생략가능). 기본값 외에 추가 변경이 필요한 옵션이 있으면 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
