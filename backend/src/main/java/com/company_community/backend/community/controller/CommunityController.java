package com.company_community.backend.community.controller;

import com.company_community.backend.community.dto.Request.PostCreateRequest;
import com.company_community.backend.community.dto.Request.PostUpdateRequest;
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
}