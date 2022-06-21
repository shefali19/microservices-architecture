package com.learning.department.service;

import com.learning.department.entity.Department;
import com.learning.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface DepartmentService {
    public Department createDepartment(Department dep);
    public Department findByDepartmentId (Long departmentId) throws Exception;
}
