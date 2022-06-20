package com.learning.user.services.service;

import com.learning.user.services.dto.ResponseTemplate;
import com.learning.user.services.dto.UserDetails;
import com.learning.user.services.entity.User;
import java.util.List;

public interface UserService {
    public User createUser(UserDetails userDetails);
    public ResponseTemplate findByUserDetails (long userId);
    public List<User> fetchAll();
    public UserDetails searchUser(UserDetails user);
}
