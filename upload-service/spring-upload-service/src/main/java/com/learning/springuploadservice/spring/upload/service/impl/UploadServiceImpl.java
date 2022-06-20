package com.learning.springuploadservice.spring.upload.service.impl;

import com.learning.springuploadservice.spring.upload.dto.DocumentMetadata;
import com.learning.springuploadservice.spring.upload.entity.UploadFileMetadata;
import com.learning.springuploadservice.spring.upload.repository.UploadRepository;
import com.learning.springuploadservice.spring.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {
    @Value("${document.upload-directory}")
    public String uploadDirectory;
    @Autowired
    UploadRepository uploadRepository;
    public String storeInPath(MultipartFile file, DocumentMetadata documentMetadata) {
       /* String fileExtension = file.getOriginalFilename().
                substring(file.getOriginalFilename().lastIndexOf("."));
       */ String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (filename.contains("..")) {
                throw new Exception(" Oh ohh ! Filename contains invalid characters," +
                        " please change and resent the request "
                        + filename);
            } else {
                // File createFile = new File(uploadDirectory + filename);
                Path copyLocation = Paths
                        .get(uploadDirectory + File.separator + filename);
                Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                log.info("File has been copied to desired destination :::::");
            }

            UploadFileMetadata uploadData = new UploadFileMetadata();
            uploadData.setUploadDirectory(uploadDirectory);
            uploadData.setDocumentType(documentMetadata.getDocumentType());
            uploadData.setUserId(documentMetadata.getUserId());
            uploadData.setDocumentname(documentMetadata.getDocumentType() +
                    String.valueOf(documentMetadata.getUserId()));
            uploadRepository.save(uploadData);
            log.info("File Upload content has been saved into database");
        } catch (IOException e) {
            log.info("Error uploading file in database");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return filename;
    }

    @Override
    public List<UploadFileMetadata> findByUserId(Long userId) {
        log.info("find document details for particular user ::::");
        return uploadRepository.findByUserId(userId);
    }

}
