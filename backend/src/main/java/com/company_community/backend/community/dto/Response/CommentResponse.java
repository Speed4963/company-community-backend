package com.company_community.backend.community.dto.Response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponse {
    private Long commentId;
    private String eno;
    private String content;
    private String insertTime;
}