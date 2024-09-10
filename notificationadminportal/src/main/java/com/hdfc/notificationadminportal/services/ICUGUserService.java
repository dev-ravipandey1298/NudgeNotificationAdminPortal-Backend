package com.hdfc.notificationadminportal.services;

import java.util.List;

import com.hdfc.notificationadminportal.dto.CUGUserDto;

public interface ICUGUserService {
	
	Integer createCUGUser(List<CUGUserDto> cugUsers);
	
	Integer deleteCUGUser(List<String> mobileNumbers);
	
	public List<CUGUserDto> getAllCUGUsers ();
	
}
