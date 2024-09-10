package com.hdfc.notificationadminportal.controller;

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

import com.hdfc.notificationadminportal.dto.CUGUserDto;
import com.hdfc.notificationadminportal.dto.ResponseCUGUserDto;
import com.hdfc.notificationadminportal.services.ICUGUserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/notification-portal/cug")
public class CUGUserController {

	private ICUGUserService iCUGUserManagementService;

	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseCUGUserDto> createCUGUsers(@Valid @RequestBody List<CUGUserDto> cugUsers) {
		Integer count = iCUGUserManagementService.createCUGUser(cugUsers);
		System.out.println(cugUsers.toString());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseCUGUserDto(Map.of("count",count), "CUG user created successfully"));
	}

	@DeleteMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseCUGUserDto> deleteCUGUsers(
			@Valid @RequestBody List<@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String> mobileNumbers) {
		Integer count = iCUGUserManagementService.deleteCUGUser(mobileNumbers);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseCUGUserDto(Map.of("count",count), "CUG user deleted successfully"));
	}
	
	@GetMapping(value = "/users")
	public ResponseEntity<ResponseCUGUserDto> getAllCUGUsers() {
		List<CUGUserDto> listCUGUsers = iCUGUserManagementService.getAllCUGUsers();
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseCUGUserDto(listCUGUsers, "Data fetched successfully"));
	}

}
