package com.compony_comunity.backend.dept.repository;

import com.compony_comunity.backend.dept.dto.DeptDto;
import com.compony_comunity.backend.dept.entity.Dept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer> {

    @Query("""
        SELECT new com.compony_comunity.backend.dept.dto.DeptDto(d.dno, d.dname, d.loc, d.dnumber) 
        FROM Dept d
        WHERE d.dname LIKE %:searchKeyword%
    """)
    Page<DeptDto> selectDeptList(@Param("searchKeyword") String searchKeyword, Pageable pageable);
}