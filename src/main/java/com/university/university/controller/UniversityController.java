package com.university.university.controller;

import com.university.university.domain.CreateCourseRequest;
import com.university.university.domain.CreateCourseResponse;
import com.university.university.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@RequestMapping(value = "/api/v1")
@RestController
@Slf4j
public class UniversityController {

	private final UniversityService service;

	@Autowired
	public UniversityController(UniversityService service){
		this.service=service;
	}

	@GetMapping(value = "/get")
	@ResponseStatus(HttpStatus.OK)
	public String getOperation() {
		log.info("Calling Get Operation");
		return "Hello at-university-api";
	}

	@PostMapping(value ="/course")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CreateCourseResponse> postCourses(@RequestBody CreateCourseRequest request){
		CreateCourseResponse response=service.createCourse(request);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
}
