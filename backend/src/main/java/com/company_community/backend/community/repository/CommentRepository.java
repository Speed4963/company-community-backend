package com.company_community.backend.community.repository;

import com.company_community.backend.community.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByPostPostId(Long postId); // 특정 게시글의 댓글들 조회
}