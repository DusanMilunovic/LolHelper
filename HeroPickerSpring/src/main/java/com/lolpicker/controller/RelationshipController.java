package com.lolpicker.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lolpicker.dto.MessageDto;
import com.lolpicker.service.RelationshipService;
import com.lolpicker.service.SecurityService;

@RestController
@RequestMapping("/relations")
public class RelationshipController {
	private final RelationshipService relationshipService;
	private final SecurityService securityService;

	@Autowired
	public RelationshipController(RelationshipService relationshipService, SecurityService securityService) {
		this.relationshipService = relationshipService;
		this.securityService = securityService;
	}
	
	@GetMapping("/{champion1}/{champion2}")
	public ResponseEntity<MessageDto> getPicks(@PathVariable("champion1") String champion1, @PathVariable("champion2") String champion2, HttpServletRequest request) {
		if (!this.securityService.checkSpam(request.getRemoteAddr())) {
			return new ResponseEntity<>(new MessageDto(this.relationshipService.checkRelationship(champion1, champion2)), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new MessageDto("Suspicious activity detected from your IP address. Too many requests sent"), HttpStatus.BAD_REQUEST);
		}
	}
}
