package com.company_community.backend.Emp.Service;

import com.company_community.backend.Emp.Dto.EmpDto;
import com.company_community.backend.Emp.Dto.LoginRequest;
import com.company_community.backend.Emp.Entity.EmpEntity;
import com.company_community.backend.Emp.Repository.EmpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 사원 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmpService {

    private final EmpRepository empRepository;

    /**
     * 사원번호, 이름, 생년월일을 이용한 로그인 로직
     * 반환 타입을 EmpEntity에서 EmpDto로 변경하여 dno를 포함시킵니다.
     */
    public EmpEntity login(LoginRequest loginRequest) {
        // 1. Repository를 통해 세 정보가 일치하는 사용자가 있는지 조회
        EmpEntity emp = empRepository.findByEnoAndEnameAndBirthdate(
                loginRequest.getEno(),
                loginRequest.getEname(),
                loginRequest.getBirthdate()
        ).orElseThrow(() -> new IllegalArgumentException("입력하신 정보와 일치하는 사원이 없습니다."));

        // 2. 일치하면 DTO로 변환하여 반환
        return convertToEntity(emp);
    }

    /**
     * 사원번호(ENO)로 사원 상세 정보 조회 (로그인 및 상세 페이지용)
     */
    public EmpEntity getEmpByEno(Long eno) {
        EmpEntity empEntity = empRepository.findByEno(eno)
                .orElseThrow(() -> new IllegalArgumentException("해당 사원번호를 가진 사원이 존재하지 않습니다. ENO: " + eno));
        return convertToEntity(empEntity);
    }

    /**
     * 이름(ENAME) 키워드로 사원 검색
     */
    public List<EmpEntity> searchEmpsByName(String ename) {
        return empRepository.findByEnameContaining(ename)
                .stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    /**
     * 특정 부서(DNO) 소속 사원 목록 조회 (조직도용)
     */
    public List<EmpEntity> getEmpsByDepartment(Integer dno) {
        return empRepository.findByDno(dno)
                .stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    /**
     * 특정 직급(JOB)의 사원 목록 조회
     */
    public List<EmpEntity> getEmpsByJob(String job) {
        return empRepository.findByJob(job)
                .stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    /**
     * Entity(Emp)를 데이터 전송 객체(EmpEntity)로 변환하는 내부 메서드
     * * 수정사항:
     * 2. 매개변수 타입을 EmpEntity에서 Emp(엔티티)로 변경
     * 3. dno 값을 Emp 엔티티의 편의 메서드(getDno)를 통해 가져옴
     */
    private EmpEntity convertToEntity(EmpEntity emp) {
        return EmpEntity.builder()
                .eno(emp.getEno())
                .ename(emp.getEname())
                .job(emp.getJob())
                .dno(emp.getDno()) // Emp 엔티티의 getDno() 호출
                .hiredate(emp.getHiredate())
                .birthdate(emp.getBirthdate())
                .pnumber(emp.getPnumber())
                .insertTime(emp.getInsertTime())
                .updateTime(emp.getUpdateTime())
                .build();
    }
}