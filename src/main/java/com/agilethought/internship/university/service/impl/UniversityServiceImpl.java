package com.agilethought.internship.university.service.impl;

import com.agilethought.internship.university.domain.CreateCourseRequest;
import com.agilethought.internship.university.domain.CreateCourseResponse;
import com.agilethought.internship.university.model.Course;
import com.agilethought.internship.university.repository.CoursesRepository;
import com.agilethought.internship.university.service.UniversityService;
import com.agilethought.internship.university.service.common.CategoryConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UniversityServiceImpl implements UniversityService {

    private final CoursesRepository repository;

    @Autowired
    public UniversityServiceImpl(CoursesRepository repository){
        this.repository = repository;
    }

    @Override
    public CreateCourseResponse createCourse(CreateCourseRequest request){
        CreateCourseResponse response = new CreateCourseResponse();
        Course course = this.requestToCourse(request);
        repository.save(course);
        log.info("Course saved with id: {}", course.get_id());
        response.setId(course.get_id().toString());
        return response;
    }

    private Course requestToCourse(CreateCourseRequest request){
        Course course = new Course();
        course.setCategory(CategoryConstants.valueOf(request.getCategory().toUpperCase()).getOrd());
        log.info("Category conversion successful");
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setImg(request.getImg());
        course.setStatus(request.getStatus());
        return course;
    }


    public List<Course> getCourses() {

        return repository.findAll();
    }

}
