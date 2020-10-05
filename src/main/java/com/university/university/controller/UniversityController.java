package com.university.university.controller;

import com.university.university.service.AtUniversityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequestMapping(value = "/api/v1/")
@RestController
@Slf4j
public class UniversityController {

	@Autowired
	private AtUniversityServiceImpl service;

	@GetMapping(value = "/course")
	@ResponseStatus(HttpStatus.OK)
	public List getOperation() {
		log.info("Calling Get Operation");

		return service.getCourses();
	}

}
