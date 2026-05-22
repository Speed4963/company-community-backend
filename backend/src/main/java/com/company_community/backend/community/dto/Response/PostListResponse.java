// 이 파일은 목록용 (글 ID, 제목, 작성자, 작성일) 입니다.
package com.company_community.backend.community.dto.Response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostListResponse {
    private Long postId;
    private String postTitle;
    private String eno; // 작성자
    private String insert_time; // 작성일
    private Long linkCount; // 조회수나 링크 수
}