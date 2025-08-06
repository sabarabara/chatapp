package com.javaapi.app.service.core.entity;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;   

@Entity
@Table(name = "attachments")
public class AttachmentEntity {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private MessageEntity message;

    @Column(name = "file_url", columnDefinition = "TEXT")
    private String fileUrl;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_size")
    private Integer fileSize;

    @CreatedDate
    @Column(name = "uploaded_at", updatable = false)
    private LocalDateTime uploadedAt;

    public AttachmentEntity() {}

    public AttachmentEntity(String id, MessageEntity message, String fileUrl, String fileType, Integer fileSize) {
        this.id = id;
        this.message = message;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }



    public String getId() {
        return id;
    }
    public MessageEntity getMessage() {
        return message;
    }
    public String getFileUrl() {
        return fileUrl;
    }
    public String getFileType() {
        return fileType;
    }
    public Integer getFileSize() {
        return fileSize;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
}
