package com.learning.springuploadservice.spring.upload.service;

import com.learning.springuploadservice.spring.upload.dto.DocumentMetadata;
import com.learning.springuploadservice.spring.upload.entity.UploadFileMetadata;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface UploadService {
    public String storeInPath(MultipartFile file, DocumentMetadata documentMetadata);

    public List<UploadFileMetadata> findByUserId(Long userId) throws Exception;
}
