package com.learning.springuploadservice.spring.upload.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "uploadFileMetadata")
@ConfigurationProperties(prefix = "document")
public class UploadFileMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "document_Id")
    private Integer documentId;
    @Column(name = "document_name")
    private String documentname;
    @Column(name = "document_type")
    private String documentType;
    @Column(name = "user_Id")
    private Long userId;
    @Column(name = "upload_directory")
    private String uploadDirectory;
}
