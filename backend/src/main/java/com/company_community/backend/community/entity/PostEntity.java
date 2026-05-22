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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String eno;
    private String postTitle;
    private String postContent;
    private String imgUrl;
    private Long linkCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private PostCategory category;

    // PostEntity.java
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments = new ArrayList<>();

    @Builder
    public PostEntity(String eno, String postTitle, String postContent, String imgUrl, PostCategory category) {
        this.eno = eno;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.imgUrl = imgUrl;
        this.linkCount = 0L;
        this.category = category;
    }

    public void update(String postTitle, String postContent, String imgUrl) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.imgUrl = imgUrl;
    }
}