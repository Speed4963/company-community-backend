package com.compony_comunity.backend.Emp.Dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 사원 정보 전송 객체 (TB_EMP 기반)
 */
@Entity // "이 클래스는 DB 테이블과 매핑된다"는 뜻
@Table(name = "TB_EMP") // 올려주신 사원 테이블명
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpDto {

    @Id
    // 사원번호 (NUMBER)
    private Long eno;

    // 사원이름 (VARCHAR2)
    private String ename;

    // 직급 (VARCHAR2)
    private String job;

    // 부서번호 (NUMBER(2,0))
    private Integer dno;

    // 입사일 (DATE)
    private LocalDate hiredate;

    // 생년월일 (DATE)
    private LocalDate birthdate;

    // 전화번호 (NUMBER)
    // 설계상 NUMBER이므로 Long을 사용하지만, 0으로 시작하는 번호를 위해
    // 실제 구현 시 String 전환을 고려해볼 수 있습니다.
    private Long pnumber;

    // 생성시간 (DATE)
    private LocalDateTime insertTime;

    // 갱신시간 (DATE)
    private LocalDateTime updateTime;
}