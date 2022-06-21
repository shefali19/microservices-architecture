package com.learning.department.service;

import com.learning.department.entity.Department;
import com.learning.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    public Department createDepartment(Department dep) {
        log.info("save department Service call::::");
        return departmentRepository.save(dep);
    }

    public Department findByDepartmentId (Long departmentId) throws Exception {
        log.info("find by Id department service Class::::");
        Optional<Department> department = Optional.ofNullable(
                departmentRepository.findByDepartmentId (departmentId));
        return department.orElseThrow(()->
                new Exception("There is no departmentId with this Id in db"));
    }
}
