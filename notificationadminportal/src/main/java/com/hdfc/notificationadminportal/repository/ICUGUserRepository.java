package com.hdfc.notificationadminportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hdfc.notificationadminportal.entity.CUGUser;

public interface ICUGUserRepository extends JpaRepository<CUGUser, String>{
	
	List<CUGUser> findByMobileNumberIn(List<String> mobileNumbers);
}
