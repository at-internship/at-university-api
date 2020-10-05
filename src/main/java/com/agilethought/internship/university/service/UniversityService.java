package com.agilethought.internship.university.service;

import com.agilethought.internship.university.domain.CreateCourseRequest;
import com.agilethought.internship.university.domain.CreateCourseResponse;
import com.agilethought.internship.university.model.Course;

import java.util.List;

public interface UniversityService {

    CreateCourseResponse createCourse(CreateCourseRequest request);
    List<Course> getCourses();
}
