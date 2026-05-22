package com.company_community.backend.community.repository;

import com.company_community.backend.community.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    // 필요에 따라 추가적인 쿼리 메서드를 정의할 수 있습니다.

    // 예: 특정 카테고리의 게시글만 찾고 싶을 때 (Spring Data JPA가 자동으로 쿼리를 생성합니다)
    List<PostEntity> findByCategoryCategoryId(Long categoryId);

    // 예: 제목으로 검색하고 싶을 때
    List<PostEntity> findByPostTitleContaining(String keyword);
}