package com.compony_comunity.backend.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
//insert time, updatetime 자동 관리
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(name = "INSERT_TIME", updatable = false)
    private LocalDateTime insertTime;

    @LastModifiedDate
    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;
}