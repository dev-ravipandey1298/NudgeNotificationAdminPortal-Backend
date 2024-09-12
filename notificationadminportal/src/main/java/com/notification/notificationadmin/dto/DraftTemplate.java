package com.notification.notificationadmin.dto;

import java.time.LocalDateTime;
import java.util.List;

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
}
