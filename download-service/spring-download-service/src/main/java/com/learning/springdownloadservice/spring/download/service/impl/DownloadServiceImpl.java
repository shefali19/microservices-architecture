package com.learning.springdownloadservice.spring.download.service.impl;

import com.learning.springdownloadservice.spring.download.service.DownloadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class DownloadServiceImpl implements DownloadService {
    @Value("${document.download-directory}")
    public String downloadDirectory;
    public Resource download(String filename){
        Path path = Paths
                .get(downloadDirectory).resolve(filename);
        try{
                Resource resource = new UrlResource(path.toUri());
                if(resource.exists() || resource.isReadable()){
                    log.info("Resource exists ::::");
                    return resource;
                }else {
                    throw new FileNotFoundException("File not found " + filename);
                }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
