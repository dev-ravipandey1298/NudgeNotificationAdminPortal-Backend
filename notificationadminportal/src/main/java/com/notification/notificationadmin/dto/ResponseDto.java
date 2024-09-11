package com.notification.notificationadmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseDto {
	private Object payload;
	private String message;
}
