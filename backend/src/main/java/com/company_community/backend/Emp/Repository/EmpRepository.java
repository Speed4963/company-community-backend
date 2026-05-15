package com.company_community.backend.Emp.Repository;

import com.company_community.backend.Emp.Entity.EmpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * TB_EMP 테이블에 접근하기 위한 Repository
 */
@Repository
public interface EmpRepository extends JpaRepository<EmpEntity, Long> {

    // 사원번호, 이름, 생년월일이 모두 일치하는 사원을 찾는 쿼리 메서드
    Optional<EmpEntity> findByEnoAndEnameAndBirthdate(Long eno, String ename, LocalDate birthdate);

    // 사원번호(ENO)로 단일 사원 조회 (로그인 시 사용)
    Optional<EmpEntity> findByEno(Long eno);

    // 이름(ENAME)으로 사원 목록 검색
    List<EmpEntity> findByEnameContaining(String ename);

    // 특정 부서(DNO)에 속한 사원 목록 조회 (조직도용)
    List<EmpEntity> findByDno(Integer dno);

    // 직급(JOB)별 사원 조회
    List<EmpEntity> findByJob(String job);
}