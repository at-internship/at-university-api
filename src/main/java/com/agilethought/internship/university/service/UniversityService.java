package com.agilethought.internship.university.service;

import com.agilethought.internship.university.domain.*;

import java.util.List;

public interface UniversityService {

    CreateCourseResponse createCourse(CreateCourseRequest request);
    List<CourseResponse> getCourses(String title);
    UpdateCourseResponse updateCourse(UpdateCourseRequest request, String id);
    void deleteCourse(String id);
}
