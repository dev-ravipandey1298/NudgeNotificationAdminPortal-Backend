package com.notification.notificationadmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.notification.notificationadmin.entity.NotificationDraftTemplate;
import com.notification.notificationadmin.repository.INotificationDraftTemplateRepository;

@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@SpringBootApplication
public class NotificationadminportalApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(NotificationadminportalApplication.class, args);
	}
	
	@Autowired
	INotificationDraftTemplateRepository iNotificationDraftTemplateRepository;

	@Override
	public void run(String... args) throws Exception {
		NotificationDraftTemplate draft1 = new NotificationDraftTemplate();
		draft1.setTemplateName("Salary Credit");
		draft1.setStatus("pending_approval");
		NotificationDraftTemplate draft2 = new NotificationDraftTemplate();
		draft2.setTemplateName("Happy Birthday");
		draft2.setStatus("pending_approval");
		iNotificationDraftTemplateRepository.saveAll(List.of(draft1, draft2));
	}

}
