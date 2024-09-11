package com.notification.notificationadmin.dto;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Working on it
 */

@Data
@AllArgsConstructor @NoArgsConstructor
public class CheckerCommentDto {
	
	@Length(max = 500, message = "Comment should not be more than 500 characters.")
	private String comment;
}
