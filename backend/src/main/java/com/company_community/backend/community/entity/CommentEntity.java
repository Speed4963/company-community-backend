package com.company_community.backend.community.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_COMMENTS")
public class CommentEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String eno; // 작성자
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id") // 외래키 설정
    private PostEntity post;

    @Builder
    public CommentEntity(String eno, String content, PostEntity post) {
        this.eno = eno;
        this.content = content;
        this.post = post;
    }
}