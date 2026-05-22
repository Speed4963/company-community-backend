package com.company_community.backend.community.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 👈 이 클래스는 테이블로 생성되지 않고, 상속받는 엔티티에게 필드만 제공함
@EntityListeners(value = {AuditingEntityListener.class}) // 👈 생성/수정 시간을 자동으로 잡아줌
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(name = "INSERT_TIME", updatable = false) // 생성일은 수정 불가
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "UPDATE_TIME")
    private LocalDateTime modDate;
}