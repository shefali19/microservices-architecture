/*
package com.learning.user.services.configuration;

import com.learning.user.services.entity.User;
import com.learning.user.services.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import java.util.Arrays;
import java.util.List;

@Component
public class AppStartup {

    @Autowired
    private StartupProperties startupProperties;

    @Bean
    public CommandLineRunner loadData(UserRepository userRepo) {
        return (args) -> {
            List<User> users = userRepo.findAll();

            if (ObjectUtils.isEmpty(users))
                userRepo.save(saveUserDetails());
        };
    }

    public User saveUserDetails() {
        User user = new User();
        user.setUsername(startupProperties.getUsername());
        user.setPassword(BCrypt.hashpw(startupProperties.getPassword(), BCrypt.gensalt()));
        user.setEmailaddress(startupProperties.getEmailAddress());
        user.setRoles(Arrays.asList("admin"));
        return user;
    }
}
*/
