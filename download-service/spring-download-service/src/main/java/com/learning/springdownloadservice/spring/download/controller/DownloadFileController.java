package com.learning.springdownloadservice.spring.download.controller;

import com.learning.springdownloadservice.spring.download.service.DownloadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
@Slf4j
public class DownloadFileController {
    @Autowired
    DownloadService downloadService;

    @GetMapping(value = "/document/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename){
        log.info("File Download ::::");
        Resource file = downloadService.download(filename);
        log.info("File Download has been started");
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\"" + file.getFilename() + "\"").body(file);
    }

}
