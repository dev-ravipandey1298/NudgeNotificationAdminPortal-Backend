package com.notification.notificationadmin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.notificationadmin.dto.DraftTemplate;
import com.notification.notificationadmin.dto.DraftTemplateDto;
import com.notification.notificationadmin.dto.Mapper;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/notification-portal")
public class GenericController {
	
	List<DraftTemplate> draftTemplatesMain = new ArrayList<>();
	
	@PostMapping("/templates/draft")
	public ResponseEntity<Response> createDraftTemplate(@RequestBody DraftTemplateDto template) {
		Integer id = null;
		if(draftTemplatesMain.isEmpty()) {
		 id = 100001;
		}else {
			id = draftTemplatesMain.get(draftTemplatesMain.size() -1).getId() + 1;
		}
		DraftTemplate templateAdded =  Mapper.mapToDraftTemplate(template, new DraftTemplate());
		templateAdded.setId(id);
		draftTemplatesMain.add(templateAdded);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new Response(Map.of("templateId", id), "Template Created Successfully"));
	}
	
	@PutMapping("/templates/draft/{templateId}")
	public ResponseEntity<Response> updateDraftTemplate(@PathVariable Integer templateId, @RequestBody DraftTemplateDto template) {
		
		Optional<DraftTemplate> draftTemplate = draftTemplatesMain.stream()
			.filter(data -> data.getId().equals(templateId)).findFirst();
		
		draftTemplatesMain.remove(draftTemplate.get());
		DraftTemplate templateUpdated =  Mapper.mapToDraftTemplate(template, new DraftTemplate());
		templateUpdated.setId(draftTemplate.get().getId());
		draftTemplatesMain.add(templateUpdated);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(template, "Template Updated Successfully"));
	}
	
	@DeleteMapping("/templates/draft/{templateId}")
	public ResponseEntity<Response> deleteDraftTemplate(@PathVariable Integer templateId) {
		
		Optional<DraftTemplate> draftTemplate = draftTemplatesMain.stream()
			.filter(data -> data.getId().equals(templateId)).findFirst();
		
		draftTemplatesMain.remove(draftTemplate.get());
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(Map.of("templateId", templateId), "Template Created Successfully"));
	}
	
	@GetMapping("/templates/draft/{templateId}")
	public ResponseEntity<Response> getDraftTemplate(@PathVariable Integer templateId) {
		Optional<DraftTemplate> draftTemplate = draftTemplatesMain.stream()
				.filter(data -> data.getId().equals(templateId)).findFirst();
		if(draftTemplate.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response(new ArrayList<>(), "Data fetched successfully"));
		}
		DraftTemplateDto dto = Mapper.mapToDraftTemplateDTO(draftTemplate.get(), new DraftTemplateDto());
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(dto, "Data fetched successfully"));
	}	
	
	@GetMapping("/templates/draft")
	public ResponseEntity<Response> getAllDraftTemplate() {
		List<DraftAllData> allData = new ArrayList<>();
		for (DraftTemplate dt: draftTemplatesMain) {
			allData.add(new DraftAllData(dt.getId().toString(), dt.getTemplateName(), "2024-09-12", ""));
		}
		
	
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(allData, "Data fetched successfully"));
	}	
	
	record Response(Object payload, String message) {
		
	}
	
	record DraftAllData(String templateId, String templateName, String createdOn, String updatedOn) {
		
	}

}
