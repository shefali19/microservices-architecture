package com.learning.department.controller;

import com.learning.department.entity.Department;
import com.learning.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @PostMapping("/")
    public Department createDepartment(@RequestBody Department dep){
        log.info("CONTROLLER:::create department");
        return departmentService.createDepartment(dep);
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable("id") Long departmentId) throws Exception {
        log.info("controller Class::::find by Id department");
        return departmentService.findByDepartmentId (departmentId);
    }
}
