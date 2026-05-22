// 이 파일은 사용자가 글을 쓸 때 보내는 데이터 (제목, 본문) 입니다.
package com.company_community.backend.community.dto.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateRequest {

    @NotBlank(message = "제목은 필수입니다.")
    private String postTitle;

    @NotBlank(message = "내용은 필수입니다.")
    private String postContent;

    private String imgUrl;
    private Long categoryId; // 카테고리 ID를 받아 매핑
    private String eno;
}