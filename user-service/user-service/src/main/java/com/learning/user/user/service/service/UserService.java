package com.learning.user.user.service.service;

import com.learning.user.user.service.VO.Department;
import com.learning.user.user.service.VO.ResponseTemplateVO;
import com.learning.user.user.service.entity.UserEntity;
import com.learning.user.user.service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestTemplate resttemplate;

    String localhostDep = "http://DEPARTMENT-SERVICE/departments/";

    public UserEntity createUser(UserEntity user) {
        log.info("save department Service call::::");
        return userRepository.save(user);
    }

    public ResponseTemplateVO findByDepartmentId (Long userId) {
        log.info("find by Id department controller Class::::");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        UserEntity user = userRepository.findByUserId(userId);

        Department department = resttemplate.getForObject(localhostDep
                        + user.getDepartmentId(), Department.class);
        vo.setDepartment(department);
        vo.setUser(user);
        return vo;
    }
}
