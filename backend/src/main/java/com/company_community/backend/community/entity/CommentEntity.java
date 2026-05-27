package com.company_community.backend.community.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_COMMENTS")
public class CommentEntity extends BaseTimeEntity {

    @Id
    @SequenceGenerator(name = "COMMENT_SEQ_GEN", sequenceName = "SEQ_TB_COMMENTS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQ_GEN")
    @Column(name = "COMMENT_ID")
    private Long commentId;

    private String eno;
    private String content;

    @Column(name = "POST_ID")
    private Long postId;

    @Builder
    public CommentEntity(String eno, String content, Long postId) {
        this.eno = eno;
        this.content = content;
        this.postId = postId;
    }
}