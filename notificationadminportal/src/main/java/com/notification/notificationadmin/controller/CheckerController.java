package com.notification.notificationadmin.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.notificationadmin.dto.CheckerCommentDto;


/**
 * Working on it
 */
@RestController
@RequestMapping("/notification-portal/templates/")
public class CheckerController {
	
	@PatchMapping(value = "{templateId}/cug-reject", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> rejectTemplateCUG(CheckerCommentDto checkerComment){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(checkerComment);
	}
	
	@PatchMapping(value = "{templateId}/cug-approved", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> approveTemplateCUG (CheckerCommentDto checkerComment){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(checkerComment);
	}
	
	@PatchMapping(value = "{templateId}/final-reject", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> rejectFinalTemplateProd (CheckerCommentDto checkerComment){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(checkerComment);
	}
	
	@PatchMapping(value = "{templateId}/final-approved", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> approveFinalTemplateProd (CheckerCommentDto checkerComment){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(checkerComment);
	}
}
