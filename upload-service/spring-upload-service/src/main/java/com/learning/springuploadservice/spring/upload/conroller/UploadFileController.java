package com.learning.springuploadservice.spring.upload.conroller;

import com.learning.springuploadservice.spring.upload.dto.DocumentMetadata;
import com.learning.springuploadservice.spring.upload.entity.UploadFileMetadata;
import com.learning.springuploadservice.spring.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadFileController {

    @Autowired
    UploadService documentService;

    @PostMapping(value = "/document")
    public ResponseEntity<?> uploadFiles( @RequestParam("document") DocumentMetadata metadata,
                                            @RequestParam("file") MultipartFile file){
        log.info("Controller:: File Upload");
        try{
            String documentname = documentService.storeInPath(file,metadata);
            log.info("File Upload has been started");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Document has been succesffully uploaded" + documentname);
        }catch(Exception uploadFailed){
            log.error("upload exception");
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Document upload exception has been occured");
        }
    }

    @GetMapping(value ="/{userid}")
    public List<UploadFileMetadata> getUploadDetails(@PathVariable("userid") Long userId){
        log.info("Controller::find document details by userId ::::");
        return documentService.findByUserId(userId);
    }
}
