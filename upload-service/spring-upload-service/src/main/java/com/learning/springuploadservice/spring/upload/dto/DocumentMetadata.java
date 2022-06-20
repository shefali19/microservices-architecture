package com.learning.springuploadservice.spring.upload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentMetadata {

    private Long userId;
    private String documentType;

}
