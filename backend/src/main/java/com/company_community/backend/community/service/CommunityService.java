package com.company_community.backend.community.service;

import com.company_community.backend.community.dto.Request.CommentCreateRequest;
import com.company_community.backend.community.dto.Request.PostCreateRequest;
import com.company_community.backend.community.dto.Request.PostUpdateRequest;
import com.company_community.backend.community.dto.Response.CommentResponse;
import com.company_community.backend.community.dto.Response.PostDetailResponse;
import com.company_community.backend.community.dto.Response.PostListResponse;
import com.company_community.backend.community.entity.CommentEntity;
import com.company_community.backend.community.entity.PostEntity;
import com.company_community.backend.community.repository.CommentRepository;
import com.company_community.backend.community.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long createPost(PostCreateRequest request) { // 👈 String eno 파라미터 제거
        // CommunityService.java 내 게시글 작성 부분
        PostEntity post = PostEntity.builder()
                .eno(request.getEno())
                .postTitle(request.getPostTitle())
                .postContent(request.getPostContent())
                .imgUrl(request.getImgUrl())
                .categoryId(request.getCategoryId()) // 👈 이 부분이 반드시 들어가야 합니다!
                .build();

        return postRepository.save(post).getPostId();
    }

    // CommunityService.java 내부에 추가
    public List<PostListResponse> getPostList() {
        return postRepository.findAll().stream() // 전체 조회
                .map(post -> PostListResponse.builder()
                        .postId(post.getPostId())
                        .postTitle(post.getPostTitle())
                        .eno(post.getEno())
                        .insert_time(post.getRegDate().toString()) // 날짜 변환
                        .linkCount(post.getLikeCount())
                        .build())
                .toList();
    }
    // CommunityService.java 내부에 추가
    public PostDetailResponse getPostDetail(Long postId) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다. ID: " + postId));

        // 2. 해당 게시글의 댓글들 가져오기
        List<CommentResponse> comments = commentRepository.findByPostId(postId).stream()
                .map(c -> CommentResponse.builder()
                        .commentId(c.getCommentId())
                        .eno(String.valueOf(c.getEno()))
                        .content(c.getContent())
                        .insertTime(c.getRegDate() != null ? c.getRegDate().toString() : null)
                        .build())
                .toList();

        return PostDetailResponse.builder()
                .postId(post.getPostId())
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .eno(post.getEno())
                .imgUrl(post.getImgUrl())
                .likeCount(post.getLikeCount())
                .insertTime(post.getRegDate() != null ? post.getRegDate().toString() : null) // 👈 여기!
                .updateTime(post.getModDate() != null ? post.getModDate().toString() : null) // 👈 여기!
                .comments(comments) // 👈 댓글 리스트 추가
                .build();
    }
    // CommunityService.java
    @Transactional
    public void updatePost(Long postId, PostUpdateRequest request) {
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // 내용 업데이트 (엔티티에 update 메서드를 만드는 것이 좋습니다)
        post.update(request.getPostTitle(), request.getPostContent(), request.getImgUrl());
    }


    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Transactional
    public void createComment(Long postId, CommentCreateRequest request) {

        System.out.println("DEBUG: postId=" + postId + ", eno=" + request.getEno() + ", content=" + request.getContent());
        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        // CommunityService.java
        CommentEntity comment = CommentEntity.builder()
                .eno(request.getEno())
                .content(request.getContent())
                .postId(postId) // 👈 이제 Long 타입인 postId를 넣으면 오류가 사라집니다.
                .build();

        commentRepository.save(comment);
    }

}