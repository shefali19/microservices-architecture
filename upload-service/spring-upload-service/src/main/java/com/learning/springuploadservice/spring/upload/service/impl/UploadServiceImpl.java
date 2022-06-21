package com.learning.springuploadservice.spring.upload.service.impl;

import com.learning.springuploadservice.spring.upload.Exception.CustomException;
import com.learning.springuploadservice.spring.upload.dto.DocumentMetadata;
import com.learning.springuploadservice.spring.upload.entity.UploadFileMetadata;
import com.learning.springuploadservice.spring.upload.repository.UploadRepository;
import com.learning.springuploadservice.spring.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
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
import java.util.Optional;

@Service
@Slf4j
public class UploadServiceImpl implements UploadService {
    @Value("${document.upload-directory}")
    public String uploadDirectory;
    private String concat = "_";
    @Autowired
    UploadRepository uploadRepository;
    public String storeInPath(MultipartFile file, DocumentMetadata documentMetadata) {
        String fileExtension = file.getOriginalFilename().
                substring(file.getOriginalFilename().lastIndexOf("."));
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(filename.isEmpty()){
                throw new CustomException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                throw new CustomException(" Filename contains invalid characters," +
                        " please change and resent the request "
                        + filename);
            } else {
                Path copyLocation = Paths
                        .get(uploadDirectory + File.separator + filename);
                Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                log.info("File has been copied to desired destination :::::");
            }
            saveToDB(file, documentMetadata);
            log.info("File Upload content has been saved into database");
        } catch (IOException e) {
            log.info("Error uploading file in database");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return filename;
    }

    private void saveToDB(MultipartFile file, DocumentMetadata documentMetadata) {
        UploadFileMetadata uploadData = new UploadFileMetadata();
        uploadData.setUploadDirectory(uploadDirectory);
        uploadData.setDocumentType(file.getContentType());
        uploadData.setUserId(documentMetadata.getUserId());
        uploadData.setDocumentname(uniqueFileName(documentMetadata));
        uploadRepository.save(uploadData);
    }

    private String uniqueFileName(DocumentMetadata documentMetadata) {
        return RandomStringUtils.randomAlphanumeric(4)
                + concat + documentMetadata.getDocumentName() + concat + documentMetadata.getUserId();
    }

    @Override
    public List<UploadFileMetadata> findByUserId(Long userId) throws Exception {
        log.info("find document details for particular user ::::");
        Optional<List<UploadFileMetadata>> department = Optional.ofNullable(
                uploadRepository.findByUserId (userId));
        return department.orElseThrow(()->
                new Exception("There is no userId with this Id in db"));
    }


}
