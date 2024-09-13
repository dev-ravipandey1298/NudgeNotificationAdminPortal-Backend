package com.notification.notificationadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Data @NoArgsConstructor
public class SearchDraftDto {
	private Integer templateId;
	private String templateName;
	private String status;
	private String createdBy;
	private String createdOn;
	private String approvedBy;

}
