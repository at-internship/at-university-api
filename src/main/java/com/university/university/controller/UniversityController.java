package com.university.university.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@RequestMapping(value = "/university/")
@RestController
@Slf4j
public class UniversityController {

	@GetMapping(value = "/get")
	@ResponseStatus(HttpStatus.OK)
	public String getOperation() {
		log.info("Calling Get Operation");
		return "Hello at-university-api";
	}

}
