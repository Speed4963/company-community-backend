// 이 파일은 사용자가 글을 수정할 때에 보내는 데이터입니다.(제목, 본문)

package com.company_community.backend.community.dto.Request;

import lombok.Getter;

@Getter
public class PostUpdateRequest {
    private String postTitle;
    private String postContent;
    private String imgUrl;
}
