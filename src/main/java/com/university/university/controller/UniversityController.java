package com.university.university.controller;

import com.university.university.service.AtUniversityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.mongodb.repository.MongoRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

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
		//return "Hello at-university-api";

		return service.getCourses();
	}

}
