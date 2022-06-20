package com.learning.api.gateway.api.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class loginDTO {
    private String username;
    private String password;
    private String token;
}
