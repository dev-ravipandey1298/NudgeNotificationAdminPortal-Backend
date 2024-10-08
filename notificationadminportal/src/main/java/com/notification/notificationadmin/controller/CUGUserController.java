package com.notification.notificationadmin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.notificationadmin.dto.CUGUserDto;
import com.notification.notificationadmin.dto.ResponseDto;
import com.notification.notificationadmin.service.ICUGUserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/notification-portal/cug")
public class CUGUserController {
	/***
	 *  All API has Maker and Checker Role.
	 */
	private ICUGUserService iCUGUserManagementService;

	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> createCUGUsers(@Valid @RequestBody List<CUGUserDto> cugUsers) {
		Integer count = iCUGUserManagementService.createCUGUser(cugUsers);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(Map.of("count",count), "CUG user created successfully"));
	}

	@DeleteMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> deleteCUGUsers(
			@Valid @RequestBody List<@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String> mobileNumbers) {
		Integer count = iCUGUserManagementService.deleteCUGUser(mobileNumbers);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(Map.of("count",count), "CUG user deleted successfully"));
	}
	
	@GetMapping(value = "/users")
	public ResponseEntity<ResponseDto> getAllCUGUsers() {
		List<CUGUserDto> listCUGUsers = iCUGUserManagementService.getAllCUGUsers();
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDto(listCUGUsers, "Data fetched successfully"));
	}

}
