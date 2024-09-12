package com.notification.notificationadmin.dto;

public class Mapper {
	
	public static DraftTemplate mapToDraftTemplate(DraftTemplateDto dto, DraftTemplate template){
		template.setTemplateName(dto.getTemplateName());
		template.setTitle(dto.getTitle());
		template.setBody(dto.getBody());
		template.setStartDate(dto.getStartDate());
		template.setEndDate(dto.getEndDate());
		template.setOccurrenceFrequency(dto.getOccurrenceFrequency());
		template.setOccurrenceUnit(dto.getOccurrenceUnit());
		template.setOccurrenceDays(dto.getOccurrenceDays());
		return template;
	}
	
	
	public static DraftTemplateDto mapToDraftTemplateDTO(DraftTemplate template, DraftTemplateDto dto){
		dto.setTemplateName(template.getTemplateName());
		dto.setTitle(template.getTitle());
		dto.setBody(template.getBody());
		dto.setStartDate(template.getStartDate());
		dto.setEndDate(template.getEndDate());
		dto.setOccurrenceFrequency(template.getOccurrenceFrequency());
		dto.setOccurrenceUnit(template.getOccurrenceUnit());
		dto.setOccurrenceDays(template.getOccurrenceDays());
		return dto;
	}
}
