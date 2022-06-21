package com.learning.user.services.dto;

import com.learning.user.services.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplate {
    private User user;
    private Department department;
    private List<DocumentDetails> document;
}
