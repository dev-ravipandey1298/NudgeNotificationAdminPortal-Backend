package com.notification.notificationadmin.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor @Data @NoArgsConstructor
public class DraftTemplateDto {

	private String templateName;
	private String title;
	private String body;
	
	private String startDate;

	private String endDate;
	private String occurrenceFrequency;
	private String occurrenceUnit;
	private List<Integer> occurrenceDays;

}
