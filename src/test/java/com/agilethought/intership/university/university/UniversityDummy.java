package com.agilethought.intership.university.university;

import com.agilethought.internship.university.domain.*;
import com.agilethought.internship.university.model.Course;

import java.util.ArrayList;
import java.util.List;

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

    public static UpdateCourseRequest getPutChartNoteRequest(){

        UpdateCourseRequest request = new UpdateCourseRequest();

        request.setCategory("JAVA");
        request.setDescription("Description");
        request.setImg("Image");
        request.setStatus(1);
        request.setTitle("Title");

        return request;

    }

    public static UpdateCourseResponse getPutChartNoteResponse(){

        UpdateCourseResponse response = new UpdateCourseResponse();

        response.setCategory("JAVA");
        response.setDescription("Description");
        response.setImg("Image");
        response.setStatus(1);
        response.setTitle("Title");

        return response;

    }

    public static List<CourseResponse> getListCourseResponseEmpty(){
        List<CourseResponse> list = new ArrayList<>();

        return list;
    }
    
    public static CourseResponse getCourseResponse(){
        CourseResponse courseResponse = new CourseResponse();

        courseResponse.set_id("1a2b3c");
        courseResponse.setCategory("JAVA");
        courseResponse.setTitle("Title");
        courseResponse.setDescription("Description");
        courseResponse.setImg("Image");
        courseResponse.setStatus(1);

        return courseResponse;
    }

}
