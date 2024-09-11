package com.notification.notificationadmin.service;

import com.notification.notificationadmin.dto.CheckerCommentDto;

public interface INotificationDraftTemplateService {
	
	Integer rejectTemplateCUG(Integer templateId, CheckerCommentDto checkerComment);
	Integer approveTemplateCUG(Integer templateId, CheckerCommentDto checkerComment);
	Integer rejectFinalTemplateProd(Integer templateId, CheckerCommentDto checkerComment);
	Integer approveFinalTemplateProd(Integer templateId, CheckerCommentDto checkerComment);
}
