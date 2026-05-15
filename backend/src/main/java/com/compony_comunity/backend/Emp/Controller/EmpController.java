package com.compony_comunity.backend.Emp.Controller;

import com.compony_comunity.backend.Emp.Dto.EmpDto;
import com.compony_comunity.backend.Emp.Service.EmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 사원 관련 HTTP 요청을 처리하는 컨트롤러
 */
@RestController
@RequestMapping("/api/emp")
@RequiredArgsConstructor
public class EmpController {

    private final EmpService empService;

    /**
     * 사원번호(ENO)로 상세 정보 조회
     * GET /api/emp/1001
     */
    @GetMapping("/{eno}")
    public ResponseEntity<EmpDto> getEmpByEno(@PathVariable Long eno) {
        return ResponseEntity.ok(empService.getEmpByEno(eno));
    }

    /**
     * 이름(ENAME)으로 사원 검색
     * GET /api/emp/search?ename=홍길동
     */
    @GetMapping("/search")
    public ResponseEntity<List<EmpDto>> searchEmpsByName(@RequestParam String ename) {
        return ResponseEntity.ok(empService.searchEmpsByName(ename));
    }

    /**
     * 특정 부서(DNO) 소속 사원 목록 조회
     * GET /api/emp/dept/10
     */
    @GetMapping("/dept/{dno}")
    public ResponseEntity<List<EmpDto>> getEmpsByDepartment(@PathVariable Integer dno) {
        return ResponseEntity.ok(empService.getEmpsByDepartment(dno));
    }

    /**
     * 특정 직급(JOB)의 사원 목록 조회
     * GET /api/emp/job/MANAGER
     */
    @GetMapping("/job/{job}")
    public ResponseEntity<List<EmpDto>> getEmpsByJob(@PathVariable String job) {
        return ResponseEntity.ok(empService.getEmpsByJob(job));
    }
}