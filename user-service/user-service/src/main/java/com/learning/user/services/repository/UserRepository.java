package com.learning.user.services.repository;

import com.learning.user.services.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> ,UserRepoUserDefined{
    User findByUserId(long userId);
}
