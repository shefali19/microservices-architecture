package com.learning.springuploadservice.spring.upload.repository;

import com.learning.springuploadservice.spring.upload.entity.UploadFileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadRepository extends JpaRepository<UploadFileMetadata,Integer> {

    List<UploadFileMetadata> findByUserId (Long userId);

}
