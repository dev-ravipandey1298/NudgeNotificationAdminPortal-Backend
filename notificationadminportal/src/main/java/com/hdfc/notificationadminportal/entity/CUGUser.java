package com.hdfc.notificationadminportal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "cug_user")
public class CUGUser extends BaseEntity{
	@Id
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	private String mobileNumber;
	
	@NotEmpty(message = "Name is required")
	@Pattern(regexp = "(^[a-zA-Z\\s]{1,20}$)", message = "Name should be valid and not more than 20 characters.")
	private String name;
}
