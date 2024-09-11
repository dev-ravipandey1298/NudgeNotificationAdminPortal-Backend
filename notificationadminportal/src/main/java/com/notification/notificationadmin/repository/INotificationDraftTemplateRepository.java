package com.notification.notificationadmin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.notification.notificationadmin.entity.NotificationDraftTemplate;

@Repository
public interface INotificationDraftTemplateRepository extends JpaRepository<NotificationDraftTemplate, Integer>{
	
}	
