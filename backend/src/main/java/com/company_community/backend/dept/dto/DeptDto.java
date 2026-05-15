package com.company_community.backend.dept.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor // JPQL 'new' 연산자가 이 생성자를 사용합니다.
@Builder
public class DeptDto {
    private Integer dno;
    private String dname;
    private String loc;
    private Integer dnumber;

    // 만약 dnumber를 제외하고 3개만 조회하고 싶다면 아래 생성자를 추가하거나
    // 전체 생성자를 호출할 때 null을 넘겨야 합니다.
    public DeptDto(Integer dno, String dname, String loc) {
        this.dno = dno;
        this.dname = dname;
        this.loc = loc;
    }
}