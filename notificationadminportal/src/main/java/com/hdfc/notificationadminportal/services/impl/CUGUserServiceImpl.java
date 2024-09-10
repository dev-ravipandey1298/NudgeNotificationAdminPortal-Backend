package com.hdfc.notificationadminportal.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hdfc.notificationadminportal.dto.CUGUserDto;
import com.hdfc.notificationadminportal.dto.CUGUserMapper;
import com.hdfc.notificationadminportal.entity.CUGUser;
import com.hdfc.notificationadminportal.exceptions.CUGUserAlreadyExistsException;
import com.hdfc.notificationadminportal.repository.ICUGUserRepository;
import com.hdfc.notificationadminportal.services.ICUGUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CUGUserServiceImpl implements ICUGUserService {

	private ICUGUserRepository iCUGUserManagementRepository;

	public Integer createCUGUser(List<CUGUserDto> cugUserDtos) {
		List<CUGUser> cugUsers = iCUGUserManagementRepository.findAllById(CUGUserMapper.mapToListOfCUGUser(cugUserDtos)
				.stream().map(CUGUser::getMobileNumber).collect(Collectors.toList()));

		if (cugUsers.isEmpty()) {
			cugUsers = iCUGUserManagementRepository.saveAllAndFlush(CUGUserMapper.mapToListOfCUGUser(cugUserDtos));
		} else {
			String existsMobileNumbers = cugUsers.stream().map(CUGUser::getMobileNumber)
					.collect(Collectors.joining(","));
			throw new CUGUserAlreadyExistsException(
					"User already exists with mobile numbers ".concat(existsMobileNumbers));
		}
		return cugUsers.size();
	}

	public Integer deleteCUGUser(List<String> mobileNumbers) {
		iCUGUserManagementRepository.deleteAllByIdInBatch(mobileNumbers);
		return mobileNumbers.size();
	}

	public List<CUGUserDto> getAllCUGUsers() {
		return CUGUserMapper
				.mapToListOfCUGUserDto(iCUGUserManagementRepository.findAll());	
	}

}
