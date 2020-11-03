package com.agilethought.internship.university;

import com.agilethought.internship.university.domain.CreateCourseRequest;
import com.agilethought.internship.university.domain.CreateCourseResponse;
import com.agilethought.internship.university.domain.UpdateCourseRequest;
import com.agilethought.internship.university.model.Course;

public class UniversityDummy {

    public static CreateCourseRequest getPostChartNoteRequest(){

        CreateCourseRequest request = new CreateCourseRequest();

        request.setCategory("JAVA");
        request.setDescription("Description");
        request.setImg("Image");
        request.setStatus(0);
        request.setTitle("Title");

        return request;

    }

    public static CreateCourseResponse getPostChartNoteResponse(){
        CreateCourseResponse response = new CreateCourseResponse();
        response.setId("1a2b3c");
        return response;
    }

    public static Course getCourse(){
        Course course = new Course();
        course.set_id("1a2b3c");
        return course;
    }

}
