package com.agilethought.internship.university.controller;

import com.agilethought.internship.university.domain.CreateCourseRequest;
import com.agilethought.internship.university.domain.CreateCourseResponse;
import com.agilethought.internship.university.service.UniversityService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import java.util.List;

@RequestMapping(value = "/api/v1")
@RestController
@Slf4j
public class UniversityController {

	private final UniversityService service;


	@Autowired
	public UniversityController(UniversityService service){
		this.service=service;
	}

	@GetMapping(value = "/course")
	@ResponseStatus(HttpStatus.OK)
	public List getOperation() {
		log.info("Calling Get Operation");
		return service.getCourses();
	}

	@PostMapping(value ="/course")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CreateCourseResponse> postCourses(
			@ApiParam(value = "Post course request", required = true) @RequestBody CreateCourseRequest request){
		CreateCourseResponse response=service.createCourse(request);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
}
