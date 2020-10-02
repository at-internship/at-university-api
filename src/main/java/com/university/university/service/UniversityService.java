package com.university.university.service;

import com.university.university.domain.CreateCourseRequest;
import com.university.university.domain.CreateCourseResponse;

public interface UniversityService {

    CreateCourseResponse createCourse(CreateCourseRequest request);

}
