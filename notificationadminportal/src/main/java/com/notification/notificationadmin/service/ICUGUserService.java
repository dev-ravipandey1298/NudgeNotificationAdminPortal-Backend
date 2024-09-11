package com.notification.notificationadmin.service;

import java.util.List;

import com.notification.notificationadmin.dto.CUGUserDto;

public interface ICUGUserService {
	
	Integer createCUGUser(List<CUGUserDto> cugUsers);
	
	Integer deleteCUGUser(List<String> mobileNumbers);
	
	public List<CUGUserDto> getAllCUGUsers ();
	
}
