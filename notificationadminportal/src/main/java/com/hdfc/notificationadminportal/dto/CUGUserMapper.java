package com.hdfc.notificationadminportal.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.hdfc.notificationadminportal.entity.CUGUser;

public class CUGUserMapper {
	
	public static CUGUserDto mapToCUGUserDto(CUGUser cugUser, CUGUserDto cugUserDto) {
		cugUserDto.setMobileNumber(cugUser.getMobileNumber());
		cugUserDto.setName(cugUser.getName());
		return cugUserDto;
	}
	
	public static CUGUser mapToCUGUser (CUGUserDto cugUserDto, CUGUser cugUser) {
		cugUser.setMobileNumber(cugUserDto.getMobileNumber());
		cugUser.setName(cugUserDto.getName());
		return cugUser;
	}
	
	public static List<CUGUser> mapToListOfCUGUser (List<CUGUserDto> cugUserDtos) {
		return cugUserDtos.stream().map(
				cugUserDto -> mapToCUGUser(cugUserDto, new CUGUser())
				).collect(Collectors.toList());
	}
	
	public static List<CUGUserDto> mapToListOfCUGUserDto (List<CUGUser> cugUsers){
		return cugUsers.stream().map(
				cugUser -> mapToCUGUserDto(cugUser, new CUGUserDto())
				).collect(Collectors.toList());
	}
	
}
