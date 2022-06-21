package com.learning.springdownloadservice.spring.download.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public interface DownloadService {
    public Resource download(String filename);
}
