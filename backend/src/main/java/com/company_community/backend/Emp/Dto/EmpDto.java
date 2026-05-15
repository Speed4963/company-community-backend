package com.company_community.backend.Emp.Dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 사원 정보 전송 객체 (TB_EMP 기반)
 */
/**
 * 사원 정보를 화면이나 API 응답으로 전달하기 위한 DTO
 * 서비스 레이어에서 엔티티를 이 DTO로 변환하여 반환합니다.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpDto {

    private Long eno;          // 사원 번호
    private String ename;      // 사원 이름
    private String job;        // 직책

    /**
     * Dept 엔티티 객체 전체를 담는 대신,
     * 클라이언트가 바로 사용할 수 있도록 부서 번호(dno)만 담습니다.
     */
    private Integer dno;

    private LocalDate hiredate;    // 입사일
    private LocalDate birthdate;   // 생년월일
    private Long pnumber;          // 전화번호

    private LocalDateTime insertTime; // 생성 시간
    private LocalDateTime updateTime; // 수정 시간
}