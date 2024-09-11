package com.notification.notificationadmin.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
//@Table(name="notification_draft_template",schema ="notification_admin")
@Table(name="notification_draft_template")
public class NotificationDraftTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_draft_template_seq")
//    @SequenceGenerator(name = "notification_draft_template_seq", sequenceName = "notification_admin.notification_draft_template_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "template_name")
    private String templateName;

//    @JdbcTypeCode(SqlTypes.JSON)
//    @Convert(converter = NotificationTemplateDTOConverter.class)
//    @Column(name = "payload", columnDefinition = "jsonb")
//    private NotificationTemplateDTO payload;

    @Column(name = "maker_comment")
    private String makerComment;

    @Column(name = "checker_cug_comment")
    private String checkerCugComment;

    @Column(name = "checker_final_comment")
    private String checkerFinalComment;

    @Column(name = "status")
    private String status;

    @Column(name = "version")
    private int version;

    @Column(name="cug_evidence")
    private String cugEvidence;

    @Column(name = "is_active")
    private boolean isActive;

    @CreatedDate
    @Column(name = "created_on",updatable = false)
    private LocalDateTime createdOn;

    @LastModifiedDate
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approved_on")
    private LocalDateTime approvedOn;
}

