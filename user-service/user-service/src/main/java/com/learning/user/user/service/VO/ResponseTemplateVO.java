package com.learning.user.user.service.VO;

import com.learning.user.user.service.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private UserEntity user;
    private Department department;
}
