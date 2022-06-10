package com.learning.department.service;

import com.learning.department.entity.Department;
import com.learning.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    public Department createDepartment(Department dep) {
        log.info("save department Service call::::");
        return departmentRepository.save(dep);
    }

    public Department findByDepartmentId (Long departmentId) {
        log.info("find by Id department controller Class::::");
        return departmentRepository.findByDepartmentId (departmentId);
    }
}
