package com.hdfc.notificationadminportal.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CUGUserDto {
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	private String mobileNumber;

	@NotEmpty(message = "Name is required")
	@Pattern(regexp = "(^[a-zA-Z\\s]{1,20}$)", message = "Name should be valid and not more than 20 characters.")
	private String name;
}
