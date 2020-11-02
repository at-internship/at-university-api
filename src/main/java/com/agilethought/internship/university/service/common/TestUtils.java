package com.agilethought.internship.university.service.common;

import org.springframework.stereotype.Component;

import com.agilethought.internship.university.domain.CourseResponse;
import com.agilethought.internship.university.domain.UpdateCourseRequest;
import com.agilethought.internship.university.domain.CreateCourseRequest;
import com.agilethought.internship.university.model.Course;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestUtils {

    public TestUtils() {
    }

    public Course getEmptyCourse() {
        Course course = new Course();
        return course;
    }

    public static CreateCourseRequest getEmptyCreateCourseRequest() {
        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        return createCourseRequest;
    }

    public static CourseResponse getEmptyCourseResponse() {
        CourseResponse courseResponse = new CourseResponse();
        return courseResponse;
    }

    public static UpdateCourseRequest getEmptyUpdateCourseRequest() {
        UpdateCourseRequest updateCourseRequest = new UpdateCourseRequest();
        return updateCourseRequest;
    }

    public static List<Course> getEmptyCourseList() {
        List<Course> courseList = new ArrayList<Course>();
        return courseList;
    }

    public static List<CreateCourseRequest> getEmptyCreateCourseRequestList() {
        List<CreateCourseRequest> createCourseRequestList = new ArrayList<CreateCourseRequest>();
        return createCourseRequestList;
    }

    public List<CreateCourseRequest> EmptySprintDomainList() {
        List<CreateCourseRequest> createCourseRequestList = new ArrayList<CreateCourseRequest>();
        return createCourseRequestList;
    }

}
