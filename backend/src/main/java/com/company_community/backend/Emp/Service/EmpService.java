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

    /**
     * 사원 정보 수정 (U)
     * JPA의 더티 체킹(Dirty Checking)을 이용하여 변경 사항을 반영합니다.
     */
    @Transactional // 데이터 수정을 위해 쓰기 트랜잭션 활성화
    public EmpEntity updateEmp(Long eno, EmpEntity updateRequest) {
        // 1. 기존 사원 정보 조회 (없으면 예외 발생)
        EmpEntity emp = empRepository.findByEno(eno)
                .orElseThrow(() -> new IllegalArgumentException("해당 사원번호를 가진 사원이 존재하지 않습니다. ENO: " + eno));

        // 2. 변경할 정보들 세팅 (필요한 컬럼만 골라서 수정 가능)
        emp.setEname(updateRequest.getEname());
        emp.setJob(updateRequest.getJob());
        emp.setHiredate(updateRequest.getHiredate());
        emp.setBirthdate(updateRequest.getBirthdate());
        emp.setPnumber(updateRequest.getPnumber());

        // 부서 변경 대응 (일반 dno 변수 필드 수정)
        emp.setDno(updateRequest.getDno());
        // 만약 관계 맺은 dept 객체도 있다면 함께 세팅해주는 것이 안전합니다.
        // emp.setDept(updateRequest.getDept());

        // 3. 변경된 엔티티를 기존 방식처럼 다시 가공해서 반환
        return convertToEntity(emp);
    }

    /**
     * 사원 정보 삭제 (D)
     */
    @Transactional // 데이터 삭제를 위해 쓰기 트랜잭션 활성화
    public void deleteEmp(Long eno) {
        // 1. 존재하는 사원인지 먼저 검증
        if (!empRepository.existsById(eno)) {
            throw new IllegalArgumentException("해당 사원번호를 가진 사원이 존재하지 않습니다. ENO: " + eno);
        }

        // 2. 삭제 실행
        empRepository.deleteById(eno);
    }
}