package com.learning.user.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    private String userId;
    private String username;
    private String emailaddress;
    private String password;
    private String departmentId;
}
