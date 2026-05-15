package com.company_community.backend.dept.controller;

import com.company_community.backend.dept.dto.DeptDto;
import com.company_community.backend.dept.entity.Dept;
import com.company_community.backend.dept.service.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j  //로그 기록 생성
@RestController
@RequestMapping("/api/dept")
@RequiredArgsConstructor  //필드를 파라미터로 가지는 생성자 자동으로 생성
public class DeptController {

    private final DeptService deptService;

    // 1. 부서 목록 조회 (검색어 + 페이징)
    // 예: GET /api/dept?searchKeyword=개발&page=0&size=10
    @GetMapping("")
    public ResponseEntity<Object> getDeptList(
            @RequestParam(defaultValue = "") String searchKeyword,
            Pageable pageable) {
        try {
            Page<DeptDto> deptPage = deptService.getDeptList(searchKeyword, pageable);
            return new ResponseEntity<>(deptPage, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 2. 부서 상세 조회
    // 예: GET /api/dept/10
    @GetMapping("/{dno}")
    public ResponseEntity<Object> getDeptDetail(@PathVariable Integer dno) {
        try {
            Optional<Dept> dept = deptService.getDeptDetail(dno);
            if (dept.isPresent()) {
                return new ResponseEntity<>(dept.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 3. 부서 생성 (C)
    @PostMapping("")
    public ResponseEntity<Object> createDept(@RequestBody Dept dept) {
        try {
            Dept savedDept = deptService.save(dept);
            return new ResponseEntity<>(savedDept, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("저장 에러: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 4. 부서 수정 (U)
    @PutMapping("/{dno}")
    public ResponseEntity<Object> updateDept(@PathVariable Integer dno, @RequestBody Dept dept) {
        try {
            deptService.update(dno, dept);
            return new ResponseEntity<>("수정 성공", HttpStatus.OK);
        } catch (Exception e) {
            log.error("수정 에러: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 5. 부서 삭제 (D)
    @DeleteMapping("/{dno}")
    public ResponseEntity<Object> deleteDept(@PathVariable Integer dno) {
        try {
            deptService.delete(dno);
            return new ResponseEntity<>("삭제 성공", HttpStatus.OK);
        } catch (Exception e) {
            log.error("삭제 에러: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}