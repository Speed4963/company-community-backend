package com.company_community.backend.Emp.Controller;

import com.company_community.backend.Emp.Dto.EmpDto;
import com.company_community.backend.Emp.Dto.LoginRequest;
import com.company_community.backend.Emp.Entity.EmpEntity;
import com.company_community.backend.Emp.Service.EmpService;
import jakarta.servlet.http.HttpSession;
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
     * 로그인 API
     * 성공 시 세션에 사원 정보를 저장합니다.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        try {
            EmpEntity loginUser = empService.login(loginRequest);

            // 로그인 성공 시 세션에 저장 (서버가 사용자를 기억하도록 함)
            session.setAttribute("loginUser", loginUser);

            return ResponseEntity.ok(loginUser);
        } catch (IllegalArgumentException e) {
            // 정보가 일치하지 않을 경우 401 Unauthorized 또는 에러 메시지 반환
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    /**
     * 로그아웃 API
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    /**
     * 사원번호(ENO)로 상세 정보 조회
     * GET /api/emp/1001
     */
    @GetMapping("/{eno}")
    public ResponseEntity<EmpEntity> getEmpByEno(@PathVariable Long eno) {
        return ResponseEntity.ok(empService.getEmpByEno(eno));
    }

    /**
     * 이름(ENAME)으로 사원 검색
     * GET /api/emp/search?ename=홍길동
     */
    @GetMapping("/search")
    public ResponseEntity<List<EmpEntity>> searchEmpsByName(@RequestParam String ename) {
        return ResponseEntity.ok(empService.searchEmpsByName(ename));
    }

    /**
     * 특정 부서(DNO) 소속 사원 목록 조회
     * GET /api/emp/dept/10
     */
    @GetMapping("/dept/{dno}")
    public ResponseEntity<List<EmpEntity>> getEmpsByDepartment(@PathVariable Integer dno) {
        return ResponseEntity.ok(empService.getEmpsByDepartment(dno));
    }

    /**
     * 특정 직급(JOB)의 사원 목록 조회
     * GET /api/emp/job/MANAGER
     */
    @GetMapping("/job/{job}")
    public ResponseEntity<List<EmpEntity>> getEmpsByJob(@PathVariable String job) {
        return ResponseEntity.ok(empService.getEmpsByJob(job));
    }
}