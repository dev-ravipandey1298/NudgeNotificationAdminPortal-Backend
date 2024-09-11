package com.notification.notificationadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notification.notificationadmin.entity.CUGUser;

@Repository
public interface ICUGUserRepository extends JpaRepository<CUGUser, String>{
	
}
