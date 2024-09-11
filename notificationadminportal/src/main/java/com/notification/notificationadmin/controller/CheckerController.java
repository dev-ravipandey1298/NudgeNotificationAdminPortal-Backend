package com.notification.notificationadmin.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.notificationadmin.dto.CheckerCommentDto;
import com.notification.notificationadmin.dto.ResponseDto;
import com.notification.notificationadmin.service.INotificationDraftTemplateService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;

/**
 * Working on it
 */
@RestController
@RequestMapping("/notification-portal/templates")
@AllArgsConstructor
@Validated
public class CheckerController {

	INotificationDraftTemplateService iNotificationDraftTemplateService;

	@PatchMapping(value = "/{templateId}/cug-reject", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> rejectTemplateCUG(
			@PathVariable @Min(value = 1, message = "templateId should not less than 1") Integer templateId,
			@Valid @RequestBody CheckerCommentDto checkerComment) {
		Integer returnId = iNotificationDraftTemplateService.rejectTemplateCUG(templateId, checkerComment);
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseDto(Map.of("templateId", returnId.toString()), "Template status updated successfully."));
	}

	@PatchMapping(value = "{templateId}/cug-approved", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> approveTemplateCUG(
			@PathVariable @Min(value = 1, message = "templateId should not less than 1") Integer templateId,
			@Valid @RequestBody CheckerCommentDto checkerComment) {
		Integer returnId = iNotificationDraftTemplateService.approveTemplateCUG(templateId, checkerComment);
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseDto(Map.of("templateId", returnId.toString()), "Template status updated successfully."));
	}

	@PatchMapping(value = "{templateId}/final-reject", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> rejectFinalTemplateProd(
			@PathVariable @Min(value = 1, message = "templateId should not less than 1") Integer templateId,
			@Valid @RequestBody CheckerCommentDto checkerComment) {
		Integer returnId = iNotificationDraftTemplateService.rejectFinalTemplateProd(templateId, checkerComment);
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseDto(Map.of("templateId", returnId.toString()), "Template status updated successfully."));
	}

	@PatchMapping(value = "{templateId}/final-approved", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> approveFinalTemplateProd(
			@PathVariable @Min(value = 1, message = "templateId should not less than 1") Integer templateId,
			@Valid @RequestBody CheckerCommentDto checkerComment) {
		Integer returnId = iNotificationDraftTemplateService.approveFinalTemplateProd(templateId, checkerComment);
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseDto(Map.of("templateId", returnId.toString()), "Template status updated successfully."));
	}
}
