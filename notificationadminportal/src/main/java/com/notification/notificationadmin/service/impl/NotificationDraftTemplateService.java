package com.notification.notificationadmin.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.notification.notificationadmin.dto.CheckerCommentDto;
import com.notification.notificationadmin.entity.NotificationDraftTemplate;
import com.notification.notificationadmin.exceptions.TemplateNotFoundException;
import com.notification.notificationadmin.repository.INotificationDraftTemplateRepository;
import com.notification.notificationadmin.service.INotificationDraftTemplateService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationDraftTemplateService implements INotificationDraftTemplateService {

	INotificationDraftTemplateRepository iNotificationDraftTemplateRepository;

	@Override
	public Integer rejectTemplateCUG(Integer templateId, CheckerCommentDto checkerComment) {
		return actionWithCommentByUserId(templateId, checkerComment, "cug_rejected", false);
	}

	@Override
	public Integer approveTemplateCUG(Integer templateId, CheckerCommentDto checkerComment) {
		return actionWithCommentByUserId(templateId, checkerComment, "cug_approved", false);
	}

	@Override
	public Integer rejectFinalTemplateProd(Integer templateId, CheckerCommentDto checkerComment) {
		return actionWithCommentByUserId(templateId, checkerComment, "prod_rejected", true);
	}

	@Override
	public Integer approveFinalTemplateProd(Integer templateId, CheckerCommentDto checkerComment) {
		return actionWithCommentByUserId(templateId, checkerComment, "prod_approved", true);
	}

	private Integer actionWithCommentByUserId(Integer templateId, CheckerCommentDto checkerComment, String status,
			boolean isProd) {
		Optional<NotificationDraftTemplate> notificationDraftTemplateOptional = iNotificationDraftTemplateRepository
				.findById(templateId);
		NotificationDraftTemplate notificationDraftTemplate = notificationDraftTemplateOptional
				.orElseThrow(() -> new TemplateNotFoundException("Template not found for ID: " + templateId));

		if (isProd) {
			notificationDraftTemplate.setCheckerFinalComment(checkerComment.getComment());
		} else {
			notificationDraftTemplate.setCheckerCugComment(checkerComment.getComment());
		}

		notificationDraftTemplate.setStatus(status);

		notificationDraftTemplate = iNotificationDraftTemplateRepository.save(notificationDraftTemplate);
		return notificationDraftTemplate.getId();
	}

}
