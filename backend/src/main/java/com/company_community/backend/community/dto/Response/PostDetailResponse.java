// 이 파일은 상세 페이지용 (글 ID, 제목, 본문, 작성자, 작성일, 댓글 목록 등) 입니다.
package com.company_community.backend.community.dto.Response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PostDetailResponse {
    private Long postId;
    private String postTitle;
    private String postContent;
    private String eno;
    private String imgUrl;
    private Long likeCount;
    private String insertTime; // 👈 여기를 수정
    private String updateTime; // 👈 여기를 수정
    private List<CommentResponse> comments; // 👈 추가된 부분
}