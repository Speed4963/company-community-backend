package com.company_community.backend.community.controller;

import com.company_community.backend.community.dto.Request.CommentCreateRequest;
import com.company_community.backend.community.dto.Request.PostCreateRequest;
import com.company_community.backend.community.dto.Request.PostUpdateRequest;
import com.company_community.backend.community.dto.Response.PostDetailResponse;
import com.company_community.backend.community.dto.Response.PostListResponse;
import com.company_community.backend.community.service.CommunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping("/posts")
    public ResponseEntity<Long> createPost(@RequestBody PostCreateRequest request) {
        return ResponseEntity.ok(communityService.createPost(request));
    }
    // CommunityController.java 내부에 추가
    @GetMapping("/posts")
    public ResponseEntity<List<PostListResponse>> getPostList() {
        return ResponseEntity.ok(communityService.getPostList());
    }

    @GetMapping("/posts/{postId}") // 👈 여기가 @PostMapping이 아닌 @GetMapping 인지 확인!
    public ResponseEntity<PostDetailResponse> getPostDetail(@PathVariable Long postId) {
        return ResponseEntity.ok(communityService.getPostDetail(postId));
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest request) {
        communityService.updatePost(postId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        communityService.deletePost(postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Void> createComment(
            @PathVariable Long postId,
            @RequestBody CommentCreateRequest request) {

        // 서비스 메서드 호출 (반환값이 없으므로 그냥 호출만 합니다)
        communityService.createComment(postId, request);

        // 데이터 없이 성공 응답만 반환
        return ResponseEntity.ok().build();
    }

    // CommunityController.java
    @PostMapping("/posts/{postId}/recommend")
    public ResponseEntity<String> recommendPost(@PathVariable Long postId) {
        communityService.recommendPost(postId);
        return ResponseEntity.ok("추천 성공");
    }

}