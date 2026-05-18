package com.company_community.backend.Emp.Entity;

import com.company_community.backend.dept.entity.Dept;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_EMP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpEntity {

    @Id
    @Column(name = "ENO")
    private Long eno; // Employee Number (Primary Key)

    @Column(name = "ENAME", nullable = false, length = 255)
    private String ename; // Employee Name

    @Column(name = "JOB", length = 255)
    private String job; // Job Title/Position



//    @Column(name = "PASSWORD", nullable = false)
//    private String password; // Login Password (Encrypted)

    @Column(name = "HIREDATE")
    private LocalDate hiredate; // Date of Hire

    @Column(name = "BIRTHDATE")
    private LocalDate birthdate; // Date of Birth

    @Column(name = "PNUMBER")
    private Long pnumber; // Phone Number

    @Column(name = "INSERT_TIME", updatable = false)
    private LocalDateTime insertTime; // Record Creation Time

    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime; // Record Update Time

    /**
     * STREAMING_CHUNK:Adding Transient dno field for Builder access...
     * @Transient: DB 테이블 컬럼과 매핑하지 않음.
     * 오직 객체 간 데이터 전달(Builder 사용 등)을 위해 존재함.
     */
    @Column(name = "DNO", insertable = false, updatable = false)
    private Integer dno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DNO", referencedColumnName = "DNO")
    private Dept dept;

    /**
     * STREAMING_CHUNK:Optimizing getDno logic...
     * 필드 dno에 값이 있으면 그것을 반환하고, 없으면 연관된 dept에서 가져옵니다.
     */
    public Integer getDno() {
        if (this.dno != null) {
            return this.dno;
        }
        return (this.dept != null) ? this.dept.getDno() : null;
    }
}