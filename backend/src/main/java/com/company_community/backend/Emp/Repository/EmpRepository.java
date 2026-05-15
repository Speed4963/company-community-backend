package com.company_community.backend.Emp.Repository;

import com.company_community.backend.Emp.Dto.EmpDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * TB_EMP 테이블에 접근하기 위한 Repository
 */
@Repository
public interface EmpRepository extends JpaRepository<EmpDto, Long> {

    // 사원번호(ENO)로 단일 사원 조회 (로그인 시 사용)
    Optional<EmpDto> findByEno(Long eno);

    // 이름(ENAME)으로 사원 목록 검색
    List<EmpDto> findByEnameContaining(String ename);

    // 특정 부서(DNO)에 속한 사원 목록 조회 (조직도용)
    List<EmpDto> findByDno(Integer dno);

    // 직급(JOB)별 사원 조회
    List<EmpDto> findByJob(String job);
}