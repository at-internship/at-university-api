package com.agilethought.internship.university.service.impl;

import com.agilethought.internship.university.domain.*;
import com.agilethought.internship.university.model.Course;
import com.agilethought.internship.university.repository.CoursesRepository;
import com.agilethought.internship.university.service.UniversityService;
import com.agilethought.internship.university.service.common.CategoryConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public List<CourseResponse> getCourses() {

        List<Course> unchanged = repository.findAll();
        List<CourseResponse> newList = new ArrayList<>();

        for (Course c:unchanged) {
            CourseResponse changed = new CourseResponse();

            changed.set_id(c.get_id());
            if(c.getCategory() == 1)
                changed.setCategory("JAVA");
            else if(c.getCategory() == 2)
                changed.setCategory("PEGA");
            else if(c.getCategory() == 3)
                changed.setCategory("JS");
            else
                changed.setCategory("UNKNOWN");
            changed.setTitle(c.getTitle());
            changed.setDescription(c.getDescription());
            changed.setImg(c.getImg());
            changed.setStatus(c.getStatus());

            newList.add(changed);
        }

        return newList;
    }

    public UpdateCourseResponse Updatecourse(UpdateCourseRequest request, String courseid) {

        UpdateCourseResponse response = new UpdateCourseResponse();
        Optional<Course> existcourse = repository.findById(courseid);

        if (existcourse != null) {
            Course newcourse = requestToUpdate(request, existcourse.get());
            repository.save(newcourse);
            if(newcourse.getCategory() == 1)
                response.setCategory("JAVA");
            else if(newcourse.getCategory() == 2)
                response.setCategory("PEGA");
            else if(newcourse.getCategory() == 3)
                response.setCategory("JS");
            response.setTitle(newcourse.getTitle());
            response.setDescription(newcourse.getDescription());
            response.setImg(newcourse.getImg());
            response.setStatus(newcourse.getStatus());
        }

        return response;
    }

    private Course requestToUpdate(UpdateCourseRequest request, Course savecourse){
        log.info("Category conversion successful");

        if (request.getCategory() != null && !request.getCategory().isEmpty())
            savecourse.setCategory(CategoryConstants.valueOf(request.getCategory().toUpperCase()).getOrd());
        else if (request.getCategory() != null && request.getCategory().isEmpty())
            savecourse.setCategory(0);

        if (request.getImg() != null)
            savecourse.setImg(request.getImg());
        else if (request.getImg() != null && request.getImg().isEmpty())
            savecourse.setImg(null);

        if (request.getTitle() != null)
            savecourse.setTitle(request.getTitle());
        else if (request.getTitle() != null && request.getTitle().isEmpty())
            savecourse.setTitle(null);

        if (request.getDescription() != null)
            savecourse.setDescription(request.getDescription());
        else if (request.getDescription() != null && request.getDescription().isEmpty())
            savecourse.setDescription(null);

        if (request.getStatus() != 0)
            savecourse.setStatus(request.getStatus());
        else
            savecourse.setStatus(0);

        return savecourse;
    }


}
