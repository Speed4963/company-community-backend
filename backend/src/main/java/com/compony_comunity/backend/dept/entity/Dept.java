package com.compony_comunity.backend.dept.entity;

import com.compony_comunity.backend.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_DEPT")
@SequenceGenerator(
        name = "SQ_DEPT_JPA",     // JPA 시퀀스 이름
        sequenceName = "SQ_DEPT", // DB 시퀀스 이름
        allocationSize = 1        // 옵션: DB 시퀀스 그대로 사용
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "dno", callSuper = false)
@Builder
public class Dept extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DEPT_JPA")
    @Column(name = "DNO")
    private Integer dno; // 부서번호 (PK)

    @Column(name = "DNAME", length = 14)
    private String dname; // 부서명

    @Column(name = "LOC", length = 13)
    private String loc; // 위치

    @Column(name = "DNUMBER")
    private Integer dnumber; // 부서 전화번호 등
}
