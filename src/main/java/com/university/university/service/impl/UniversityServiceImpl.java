package com.university.university.service.impl;

import com.university.university.domain.CreateCourseRequest;
import com.university.university.domain.CreateCourseResponse;
import com.university.university.model.Course;
import com.university.university.repository.CoursesRepository;
import com.university.university.service.UniversityService;
import com.university.university.service.common.CategoryConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UniversityServiceImpl implements UniversityService {

    private final CoursesRepository repository;

    @Autowired
    public UniversityServiceImpl(CoursesRepository repository){
        this.repository=repository;
    }

    @Override
    public CreateCourseResponse createCourse(CreateCourseRequest request){
        CreateCourseResponse response = new CreateCourseResponse();
        Course course = this.requestToCourse(request);
        repository.save(course);
        response.setId(course.get_id().toString());
        return response;
    }

    private Course requestToCourse(CreateCourseRequest request){
        Course course = new Course();
        course.setCategory(CategoryConstants.valueOf(request.getCategory().toUpperCase()).getOrd());
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setImg(request.getImg());
        course.setStatus(request.getStatus());
        return course;
    }

}
