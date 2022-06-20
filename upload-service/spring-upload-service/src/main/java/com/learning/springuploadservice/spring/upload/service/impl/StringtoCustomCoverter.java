package com.learning.springuploadservice.spring.upload.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.springuploadservice.spring.upload.dto.DocumentMetadata;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * This class will convert the requestparam object into JSON format
 */
@Component
public class StringtoCustomCoverter implements Converter<String, DocumentMetadata> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public DocumentMetadata convert(String source) {
        return objectMapper.readValue(source, DocumentMetadata.class);
    }

}
