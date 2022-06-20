package com.learning.user.services.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("UserRepoUserDefined")
public interface UserRepoUserDefined {
}
