package com.notification.notificationadmin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.notification.notificationadmin.dto.CheckerCommentDto;
import com.notification.notificationadmin.dto.DraftTemplate;
import com.notification.notificationadmin.dto.DraftTemplateDto;
import com.notification.notificationadmin.dto.DraftTemplateDto2;
import com.notification.notificationadmin.dto.Mapper;
import com.notification.notificationadmin.dto.SearchDraftDto;
import com.notification.notificationadmin.dto.TemplateStatusEnum;

import jakarta.validation.Valid;
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
		if (draftTemplatesMain.isEmpty()) {
			id = 100001;
		} else {
			id = draftTemplatesMain.get(draftTemplatesMain.size() - 1).getId() + 1;
		}
		DraftTemplate templateAdded = Mapper.mapToDraftTemplate(template, new DraftTemplate());
		templateAdded.setId(id);
		templateAdded.setStatus(TemplateStatusEnum.DRAFT.name());
		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		templateAdded.setCreatedOn(modifiedDate);
		draftTemplatesMain.add(templateAdded);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new Response(Map.of("templateId", id), "Template Created Successfully"));
	}

	@PutMapping("/templates/draft/{templateId}")
	public ResponseEntity<Response> updateDraftTemplate(@PathVariable Integer templateId,
			@RequestBody DraftTemplateDto template) {

		Optional<DraftTemplate> draftTemplate = draftTemplatesMain.stream()
				.filter(data -> data.getId().equals(templateId)).findFirst();

		draftTemplatesMain.remove(draftTemplate.get());
		DraftTemplate templateUpdated = Mapper.mapToDraftTemplate(template, draftTemplate.get());
		templateUpdated.setId(draftTemplate.get().getId());
		templateUpdated.setStatus(TemplateStatusEnum.DRAFT.name());
		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		templateUpdated.setUpdatedOn(modifiedDate);
		draftTemplatesMain.add(templateUpdated);
		return ResponseEntity.status(HttpStatus.OK).body(new Response(template, "Template Updated Successfully"));
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
		if (draftTemplate.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response(new ArrayList<>(), "Data fetched successfully"));
		}
		DraftTemplateDto dto = Mapper.mapToDraftTemplateDTO(draftTemplate.get(), new DraftTemplateDto());
		return ResponseEntity.status(HttpStatus.OK).body(new Response(dto, "Data fetched successfully"));
	}

	@GetMapping("/templates/draft")
	public ResponseEntity<Response> getAllDraftTemplate() {
		List<DraftAllData> allData = new ArrayList<>();
		for (DraftTemplate dt : draftTemplatesMain) {
			if (dt.getStatus().equals(TemplateStatusEnum.DRAFT.toString())) {
				allData.add(new DraftAllData(dt.getId().toString(), dt.getTemplateName(), "2024-09-12", ""));
			}
		}

		return ResponseEntity.status(HttpStatus.OK).body(new Response(allData, "Data fetched successfully"));
	}

	@PutMapping("/templates/cug-approval")
	public ResponseEntity<Response> submitForCugApproval(@RequestBody DraftTemplateDto2 template) {
		Integer templateId = template.getTemplateId();
		Optional<DraftTemplate> draftTemplate = draftTemplatesMain.stream()
				.filter(data -> data.getId().equals(templateId)).findFirst();

		draftTemplatesMain.remove(draftTemplate.get());
		DraftTemplate templateUpdated = Mapper.mapToDraftTemplate(template, draftTemplate.get());
		templateUpdated.setId(draftTemplate.get().getId());
		templateUpdated.setStatus(TemplateStatusEnum.APPROVAL_PENDING_CUG.name());
		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		templateUpdated.setUpdatedOn(modifiedDate);
		draftTemplatesMain.add(templateUpdated);
		return ResponseEntity.status(HttpStatus.OK).body(
				new Response(Map.of("templateId", templateId), "Template submitted for cug approval successfully"));
	}

	@GetMapping("/all")
	public List<DraftTemplate> getAll() {
		return draftTemplatesMain;
	}

	@GetMapping("/templates")
	public ResponseEntity<Response> getTemplatesBySearchCriteria(@RequestParam(required = false) List<String> status,
			@RequestParam(required = false) Integer templateId, @RequestParam(required = false) String templateName) {
		List<SearchDraftDto> allData = new ArrayList<>();
		List<DraftTemplate> templates = new ArrayList<>();
		if (status != null) {
			templates = draftTemplatesMain.stream().filter(item -> status.contains(item.getStatus()))
					.collect(Collectors.toList());

		} else {
			templates = draftTemplatesMain;
		}

		for (DraftTemplate dt : templates) {
			allData.add(Mapper.mapToSearchDraftTemplateDTO(dt, new SearchDraftDto()));
		}

		return ResponseEntity.status(HttpStatus.OK).body(new Response(allData, "Data fetched successfully"));
	}

	@PatchMapping("/templates/{templateId}/cug-approved")
	public ResponseEntity<Response> approvedCUG(@PathVariable Integer templateId,
			@Valid @RequestBody CheckerCommentDto comment) {
		Optional<DraftTemplate> draftTemplate = draftTemplatesMain.stream()
				.filter(data -> data.getId().equals(templateId)).findFirst();
		DraftTemplate dt = draftTemplate.get();
		draftTemplatesMain.remove(draftTemplate.get());
		dt.setStatus(TemplateStatusEnum.CUG_APPROVED.name());
		dt.setCheckerCugComment(comment.getComment());
		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		dt.setUpdatedOn(modifiedDate);
		draftTemplatesMain.add(dt);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(Map.of("templateId", templateId), "Template status updated successfully"));
	}
	
	@PatchMapping("/templates/{templateId}/final-approval")
    public ResponseEntity<Response> handleFileUpload(
    		@PathVariable Integer templateId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("comment") String comment) {
		
		Optional<DraftTemplate> draftTemplate = draftTemplatesMain.stream()
				.filter(data -> data.getId().equals(templateId)).findFirst();
		
		DraftTemplate dt = draftTemplate.get();
		draftTemplatesMain.remove(draftTemplate.get());
		dt.setFile(file);
		dt.setStatus(TemplateStatusEnum.APPROVAL_PENDING_PROD.toString());
		dt.setMakerComment(comment);
		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		dt.setUpdatedOn(modifiedDate);
		draftTemplatesMain.add(dt);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(Map.of("templateId", templateId), "Template submitted for final approval"));
    }
	
	@PatchMapping("/templates/{templateId}/cug-reject")
	public ResponseEntity<Response> rejectCUG(@PathVariable Integer templateId,
			@RequestBody CheckerCommentDto comment) {
		Optional<DraftTemplate> draftTemplate = draftTemplatesMain.stream()
				.filter(data -> data.getId().equals(templateId)).findFirst();
		DraftTemplate dt = draftTemplate.get();
		draftTemplatesMain.remove(draftTemplate.get());
		dt.setStatus(TemplateStatusEnum.REJECTED.name());
		dt.setCheckerCugComment(comment.getComment());
		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		dt.setUpdatedOn(modifiedDate);
		draftTemplatesMain.add(dt);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(Map.of("templateId", templateId), "Template status updated successfully"));
	}

	@PatchMapping("/templates/{templateId}/final-approved")
	public ResponseEntity<Response> approvedPROD(@PathVariable Integer templateId,
			@Valid @RequestBody CheckerCommentDto comment) {
		Optional<DraftTemplate> draftTemplate = draftTemplatesMain.stream()
				.filter(data -> data.getId().equals(templateId)).findFirst();
		DraftTemplate dt = draftTemplate.get();
		draftTemplatesMain.remove(draftTemplate.get());
		dt.setStatus(TemplateStatusEnum.PROD_APPROVED.name());
		dt.setCheckerFinalComment(comment.getComment());
		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		dt.setUpdatedOn(modifiedDate);
		draftTemplatesMain.add(dt);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(Map.of("templateId", templateId), "Template status updated successfully"));
	}
	
	@PatchMapping("/templates/{templateId}/final-reject")
	public ResponseEntity<Response> rejectPROD(@PathVariable Integer templateId,
			@Valid @RequestBody CheckerCommentDto comment) {
		Optional<DraftTemplate> draftTemplate = draftTemplatesMain.stream()
				.filter(data -> data.getId().equals(templateId)).findFirst();
		DraftTemplate dt = draftTemplate.get();
		draftTemplatesMain.remove(draftTemplate.get());
		dt.setStatus(TemplateStatusEnum.REJECTED.name());
		dt.setCheckerFinalComment(comment.getComment());
		Date date = new Date();
		String modifiedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		dt.setUpdatedOn(modifiedDate);
		draftTemplatesMain.add(dt);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(Map.of("templateId", templateId), "Template status updated successfully"));
	}

	record Response(Object payload, String message) {

	}

	record DraftAllData(String templateId, String templateName, String createdOn, String updatedOn) {

	}

}
