package com.lolpicker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.lolpicker.model.ChampionSelect;
import com.lolpicker.service.SampleService;

@RestController
@RequestMapping("/api")
public class SampleController {

	private final SampleService sampleService;

	@Autowired
	public SampleController(SampleService sampleService) {
		this.sampleService = sampleService;
	}
	
	@RequestMapping(value = "/cs", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ChampionSelect> getSampleCs() {
		return new ResponseEntity<>(sampleService.sample(), HttpStatus.OK);
	}
}
