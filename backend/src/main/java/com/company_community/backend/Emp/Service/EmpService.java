package com.company_community.backend.Emp.Service;

import com.company_community.backend.Emp.Dto.EmpDto;
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
     * 사원번호(ENO)로 사원 상세 정보 조회 (로그인 및 상세 페이지용)
     */
    public EmpDto getEmpByEno(Long eno) {
        EmpDto emp = empRepository.findByEno(eno)
                .orElseThrow(() -> new IllegalArgumentException("해당 사원번호를 가진 사원이 존재하지 않습니다. ENO: " + eno));
        return convertToDto(emp);
    }

    /**
     * 이름(ENAME) 키워드로 사원 검색
     */
    public List<EmpDto> searchEmpsByName(String ename) {
        return empRepository.findByEnameContaining(ename)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * 특정 부서(DNO) 소속 사원 목록 조회 (조직도용)
     */
    public List<EmpDto> getEmpsByDepartment(Integer dno) {
        return empRepository.findByDno(dno)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * 특정 직급(JOB)의 사원 목록 조회
     */
    public List<EmpDto> getEmpsByJob(String job) {
        return empRepository.findByJob(job)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Entity를 Dto로 변환하는 내부 메서드
     * dno는 참조키이므로 연관된 Department 엔티티 객체에서 값을 추출하도록 수정했습니다.
     */
    private EmpDto convertToDto(EmpDto emp) {
        return EmpDto.builder()
                .eno(emp.getEno())
                .ename(emp.getEname())
                .job(emp.getJob())
                /* * dno는 참조키(FK)이므로 Emp 엔티티 내의 Department 객체를 통해 가져옵니다.
                 * 부서 정보가 없는 경우(Null)를 대비해 안전하게 처리합니다.
                 */
                .dno(emp.getDno())
                .hiredate(emp.getHiredate())
                .birthdate(emp.getBirthdate())
                .pnumber(emp.getPnumber())
                .insertTime(emp.getInsertTime())
                .updateTime(emp.getUpdateTime())
                .build();
    }
}