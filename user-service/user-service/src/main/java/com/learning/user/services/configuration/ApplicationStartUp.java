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
public class ApplicationStartUp {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StartupProperties startupProperties;

    @Bean
    public CommandLineRunner loadData(){
        return (args) -> {
            List<User> users = userRepository.findAll();
            if(ObjectUtils.isEmpty(users)){
                this.userRepository.save(User.builder().username(startupProperties.getUsername())
                        .password(BCrypt.hashpw(startupProperties.getPassword()
                                , BCrypt.gensalt())).roles(Arrays.asList("ADMIN"))
                        .emailaddress(startupProperties.getEmailAddress()).build());
            }
        };
    }

}
*/
