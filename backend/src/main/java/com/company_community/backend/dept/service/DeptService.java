package com.company_community.backend.dept.service;

import com.company_community.backend.Emp.Repository.EmpRepository;
import com.company_community.backend.dept.dto.DeptDto;
import com.company_community.backend.dept.entity.Dept;
import com.company_community.backend.dept.repository.DeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeptService {

    private final DeptRepository deptRepository;
//     private final EmpRepository empRepository;

    // 1. 부서 전체 조회 (페이징 + 검색)
    public Page<DeptDto> getDeptList(String searchKeyword, Pageable pageable) {
        return deptRepository.selectDeptList(searchKeyword, pageable);
    }

    // 2. 부서 상세 조회 (단일)
    public Optional<Dept> getDeptDetail(Integer dno) {
        return deptRepository.findById(dno);
    }
    // 3. 부서 생성 (C)
    @Transactional // 쓰기 작업이므로 트랜잭션 추가
    public Dept save(Dept dept) {
        return deptRepository.save(dept);
    }

    // 4. 부서 수정 (U)
    @Transactional
    public void update(Integer dno, Dept dept) {
        // 기존 부서 정보 조회
        Dept target = deptRepository.findById(dno)
                .orElseThrow(() -> new IllegalArgumentException("해당 부서가 없습니다. dno=" + dno));

        // 데이터 변경 (JPA 더티 체킹에 의해 트랜잭션이 끝날 때 자동 업데이트 됨)
        target.setDname(dept.getDname());
        target.setLoc(dept.getLoc());
        target.setDnumber(dept.getDnumber());
        // updateTime은 BaseTimeEntity에 의해 자동 변경됨
    }

    // 5. 부서 삭제 (D)
    @Transactional
    public void delete(Integer dno) {
        deptRepository.deleteById(dno);
    }
}