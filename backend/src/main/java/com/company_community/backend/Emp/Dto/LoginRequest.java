package com.company_community.backend.Emp.Dto;

import lombok.*;

import java.time.LocalDate;

/**
 * 로그인 시 클라이언트로부터 전달받는 데이터 객체
 * 사원번호, 이름, 생년월일 3종 세트를 사용합니다.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {
    private Long eno;          // 사원번호
    private String ename;      // 이름
    private LocalDate birthdate; // 생년월일
}