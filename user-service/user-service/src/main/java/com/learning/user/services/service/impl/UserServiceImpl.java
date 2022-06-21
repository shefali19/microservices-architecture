package com.learning.user.services.service.impl;

import com.learning.user.services.constants.Constants;
import com.learning.user.services.dto.Department;
import com.learning.user.services.dto.DocumentDetails;
import com.learning.user.services.dto.ResponseTemplate;
import com.learning.user.services.dto.UserDetails;
import com.learning.user.services.entity.User;
import com.learning.user.services.repository.UserRepository;
import com.learning.user.services.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static com.learning.user.services.entity.UserQuery.createQueryToFetchUserDetails;

/**
 This class is used to implement service for usr and fetch all required user info :
 User Details , Department Details , upload documents and download documents
 **/
@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    final Constants constants = new Constants();
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate resttemplate;
    @PersistenceContext
    private EntityManager entityManager;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDetails userDetails) {
        log.info("Service Call:::: Save user details");
        User userInfo = getUser(userDetails);
        return userInfo;
    }
    public ResponseTemplate findByUserDetails (long userId) {
        log.info("Service Call::::to fetch the all information of an user with " +
                "uploaded documents and department details ::::");
        ResponseTemplate vo = getResponse(userId);
        return vo;
    }

    public List<User> fetchAll() {
        log.info("Service Call::::fetch all users details");
        return userRepository.findAll();
    }

    public UserDetails searchUser(UserDetails user) {
        log.info("Service Call::::search user with input parameters");
        UserDetails fetchUser = searchUserDetails(user);
        return fetchUser;
    }

    /**
        This method will fetch all user details with required departments and documents uploaded
     **/
    private ResponseTemplate getResponse(long userId) {
        ResponseTemplate vo = new ResponseTemplate();
        User user = userRepository.findByUserId(userId);
        Department department = resttemplate.getForObject(constants.localhostDep
                        + user.getDepartmentId(), Department.class);
        ResponseEntity<DocumentDetails[]> responseEntity = resttemplate.getForEntity(constants.localhostDocumentDetails
                        + user.getUserId(), DocumentDetails[].class);
        List<DocumentDetails> document = Arrays.asList(responseEntity.getBody());
        /*List<DocumentDetails> document = (List<DocumentDetails>) resttemplate.getForObject(constants.localhostDocumentDetails
                        + user.getUserId(), DocumentDetails.class);*/
        vo.setDepartment(department);
        vo.setUser(user);
        vo.setDocument(document);
        return vo;
    }

    /**
       This method will search particular user
    **/
    private UserDetails searchUserDetails(UserDetails user) {
        List<Object[]> results = entityManager.createNativeQuery(createQueryToFetchUserDetails.
                apply(user)).getResultList();
        results.forEach(result -> {
            user.setUserId(result[constants.user_id].toString());
            user.setUsername(result[constants.user_name].toString());
            user.setPassword(result[constants.password].toString());
            user.setEmailaddress(result[constants.email_address].toString());
            user.setDepartmentId(result[constants.department_Id].toString());
        });

        return user;
}
    /**
         This method will upload the content of user into database
     **/
    private User getUser(UserDetails userDetails) {
        User user = new User();
        user.setEmailaddress(userDetails.getEmailaddress());
        user.setPassword(userDetails.getPassword());
        user.setUsername(userDetails.getUsername());
        user.setDepartmentId(userDetails.getDepartmentId());
        User userInfo = userRepository.save(user);
        return userInfo;
    }

}
