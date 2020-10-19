package com.agilethought.internship.university.service;

import com.agilethought.internship.university.domain.*;

import java.util.List;

public interface UniversityService {

    CreateCourseResponse createCourse(CreateCourseRequest request);
    List<CourseResponse> getCourses();
    UpdateCourseResponse Updatecourse(UpdateCourseRequest request, String id);
}
