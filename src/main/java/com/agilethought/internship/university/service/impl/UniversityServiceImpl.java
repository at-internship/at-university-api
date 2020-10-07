package com.agilethought.internship.university.service.impl;

import com.agilethought.internship.university.domain.CreateCourseRequest;
import com.agilethought.internship.university.domain.CreateCourseResponse;
import com.agilethought.internship.university.model.Course;
import com.agilethought.internship.university.repository.CoursesRepository;
import com.agilethought.internship.university.service.UniversityService;
import com.agilethought.internship.university.service.common.CategoryConstants;
import com.agilethought.internship.university.domain.CourseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    public List<CourseResponse> getCourses() {

        List<Course> unchanged = repository.findAll();
        List<CourseResponse> newList = new ArrayList<>();

        for (Course c:unchanged) {
            CourseResponse changed = new CourseResponse();

            changed.set_id(c.get_id());
            int indicator = c.getCategory();
            if(indicator == 1)
                changed.setCategory("JAVA");
            else if(indicator == 2)
                changed.setCategory("PEGA");
            else
                changed.setCategory("JS");
            changed.setTitle(c.getTitle());
            changed.setDescription(c.getDescription());
            changed.setImg(c.getImg());
            changed.setStatus(c.getStatus());

            newList.add(changed);
        }

        return newList;
    }

}
