package com.agilethought.internship.university.controller;

import com.agilethought.internship.university.domain.*;
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
	public List<CourseResponse> getOperation() {
		log.info("UniversityController.getOperation - Calling Get Operation");
		return service.getCourses();
	}

	@PostMapping(value ="/course")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CreateCourseResponse> postCourses(
			@ApiParam(value = "Post course request", required = true) @RequestBody CreateCourseRequest request){
		log.info("UniversityController.postCourses - operation request: {}", request);
		CreateCourseResponse response=service.createCourse(request);
		log.info("UniversityController.postCourses - POST operation was successful: {}", response);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}

	@PutMapping(value = {"/course/","/course/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<UpdateCourseResponse> putOperation(
			@RequestBody UpdateCourseRequest request, @PathVariable(required = false) String id) {
	    log.info("UniversityController.putOperation - operation request: {}, id: {}", request, id);
		UpdateCourseResponse response = service.updateCourse(request, id);
		log.info("UniversityController.putOperation - PUT operation was successful: {}", response);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = {"/course/","/course/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DeleteCourseResponse> deleteOperation(@PathVariable(required = false) String id){
		log.info("UniversityController.deleteOperation - id received for the delete operation:{}",id);
		service.deleteCourse(id);
		log.info("UniversityController.deleteOperation - DELETE operation was successful, deleted id:{}", id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
