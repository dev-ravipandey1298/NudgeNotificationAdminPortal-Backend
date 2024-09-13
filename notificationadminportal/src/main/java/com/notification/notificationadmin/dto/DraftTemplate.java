package com.notification.notificationadmin.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data @NoArgsConstructor
public class DraftTemplate {
	private Integer id;
	private String templateName;
	private String title;
	private String body;
	private String startDate;
	private String endDate;
	private String occurrenceFrequency;
	private String occurrenceUnit;
	private List<Integer> occurrenceDays;
	private String makerComment;
	private String checkerCugComment;
	private String checkerFinalComment;
	private String status;
	private boolean isActive;
	private String createdOn;
	private String updatedOn;
	private String createdBy;
	private String updatedBy;
	private String approvedBy;
	private String approvedOn;
	private MultipartFile file;
}
