package com.company_community.backend.community.dto.Request;

import lombok.Getter;

@Getter
public class CommentCreateRequest {
    private String content;
    private String eno;
}