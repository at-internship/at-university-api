package com.agilethought.internship.university;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class UniversityApplication {

	private static Logger logger = LogManager.getLogger(UniversityApplication.class);

	public static void main(String[] args) {
		logger.error("error log message!");
		logger.trace ("trace log message!");
		logger.debug ("Debug log message!");
		logger.info ("info log message!");
		logger.warn ("warn log message!");
		logger.fatal ("fatal log message!");

		SpringApplication.run(UniversityApplication.class, args);
	}

}
