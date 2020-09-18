package com.heroku.heroku.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RequestMapping(value = "/heroku/")
@RestController
@Slf4j
public class HerokuController {

	@GetMapping(value = "/get")
	@ResponseStatus(HttpStatus.OK)
	public String getOperation() {
		log.info("Calling Get Operation");
		return "Hello World";
	}

}
