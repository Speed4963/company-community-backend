package com.company_community.backend.community.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_POSTS")
public class PostEntity extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_POSTS_SEQ_GEN")
    @SequenceGenerator(name = "TB_POSTS_SEQ_GEN", sequenceName = "tb_posts_seq", allocationSize = 1)
    private Long postId;

    private String eno;
    // PostEntity.java
    @Column(length = 200) // 👈 이 길이가 DB 테이블의 실제 크기보다 큰지 확인하세요.
    private String postTitle;
    @Column(name = "post_content", length = 4000)
    private String postContent;
    private String imgUrl;
    private Long likeCount;

    // 👈 객체 관계(ManyToOne) 대신, DB 컬럼명 그대로 매핑
    @Column(name = "category_id")
    private Long categoryId;

    @Builder
    public PostEntity(String eno, String postTitle, String postContent, String imgUrl, Long categoryId) {
        this.eno = eno;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.imgUrl = imgUrl;
        this.likeCount = 0L;
        this.categoryId = categoryId; // 👈 숫자 그대로 할당
    }

    public void update(String postTitle, String postContent, String imgUrl) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.imgUrl = imgUrl;
    }

    // 1. 추천 수 증가 메서드
    public void increaseLikeCount() {
        this.likeCount += 1;
    }


}