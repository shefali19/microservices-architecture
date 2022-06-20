package com.learning.user.services.service.impl;

import com.learning.user.services.repository.UserRepoUserDefined;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserRepoUserDefinedImpl implements UserRepoUserDefined {

    @PersistenceContext
    private EntityManager entityManager;

}
