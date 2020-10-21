package com.agilethought.internship.university.service.impl;

import com.agilethought.internship.university.domain.*;
import com.agilethought.internship.university.model.Course;
import com.agilethought.internship.university.repository.CoursesRepository;
import com.agilethought.internship.university.service.UniversityService;
import com.agilethought.internship.university.service.common.CategoryConstants;
import com.agilethought.internship.university.service.common.OrikaTransformer;
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
    private final OrikaTransformer orikaTransformer;

    @Autowired
    public UniversityServiceImpl(CoursesRepository repository, OrikaTransformer orikaTransformer){
        this.repository = repository;
        this.orikaTransformer = orikaTransformer;
    }

    @Override
    public CreateCourseResponse createCourse(CreateCourseRequest request){
        CreateCourseResponse response = new CreateCourseResponse();
        Course course = orikaTransformer.transformer(request);
        repository.save(course);
        log.info("Course saved with id: {}", course.get_id());
        response.setId(course.get_id().toString());
        return response;
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

    public UpdateCourseResponse updateCourse(UpdateCourseRequest request, String courseid) {

        UpdateCourseResponse response = new UpdateCourseResponse();
        Optional<Course> existCourse = repository.findById(courseid);

        if (existCourse != null) {
            Course newCourse = requestToUpdate(request, existCourse.get());
            repository.save(newCourse);
            if(newCourse.getCategory() == 1)
                response.setCategory("JAVA");
            else if(newCourse.getCategory() == 2)
                response.setCategory("PEGA");
            else if(newCourse.getCategory() == 3)
                response.setCategory("JS");
            response.setTitle(newCourse.getTitle());
            response.setDescription(newCourse.getDescription());
            response.setImg(newCourse.getImg());
            response.setStatus(newCourse.getStatus());
        }

        return response;
    }

    private Course requestToUpdate(UpdateCourseRequest request, Course saveCourse){

        if (request.getCategory() != null && !request.getCategory().isEmpty())
            saveCourse.setCategory(CategoryConstants.valueOf(request.getCategory().toUpperCase()).getOrd());
        else if (request.getCategory() != null && request.getCategory().isEmpty())
            saveCourse.setCategory(0);

        if (request.getImg() != null)
            saveCourse.setImg(request.getImg());
        else if (request.getImg() != null && request.getImg().isEmpty())
            saveCourse.setImg(null);

        if (request.getTitle() != null)
            saveCourse.setTitle(request.getTitle());
        else if (request.getTitle() != null && request.getTitle().isEmpty())
            saveCourse.setTitle(null);

        if (request.getDescription() != null)
            saveCourse.setDescription(request.getDescription());
        else if (request.getDescription() != null && request.getDescription().isEmpty())
            saveCourse.setDescription(null);

        if (request.getStatus() != 0)
            saveCourse.setStatus(request.getStatus());
        else
            saveCourse.setStatus(0);

        log.info("Category conversion successful");
        return saveCourse;
    }


}
