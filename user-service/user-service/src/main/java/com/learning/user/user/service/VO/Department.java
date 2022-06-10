package com.learning.user.user.service.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private long departmentId;
    private String departmentname;
    private String departmentAddress;
    private String departmentCode;

}
