package com.agilethought.internship.university.service.impl;

import com.agilethought.internship.university.domain.*;
import com.agilethought.internship.university.model.Course;
import com.agilethought.internship.university.repository.CoursesRepository;
import com.agilethought.internship.university.service.UniversityService;
import com.agilethought.internship.university.service.common.CategoryConstants;
import com.agilethought.internship.university.service.common.OrikaTransformer;
import com.agilethought.internship.university.validations.Validator;
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

    @Autowired
    private Validator validator;

    @Override
    public CreateCourseResponse createCourse(CreateCourseRequest request){
        CreateCourseResponse response = new CreateCourseResponse();
        Validator.requestValidation(request);
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
            CourseResponse courseResponse = new CourseResponse();
            courseResponse = orikaTransformer.transformer(c);
            newList.add(courseResponse);
        }

        return newList;
    }

    public UpdateCourseResponse updateCourse(UpdateCourseRequest request, String id) {

        UpdateCourseResponse response = new UpdateCourseResponse();
        Optional<Course> course = repository.findById(id);

        if (course != null) {
            Validator.requestValidationToUpdate(request);
            Course newCourse = requestToUpdate(request, course.get());
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

            saveCourse.setCategory(CategoryConstants.valueOf(request.getCategory().toUpperCase()).getOrd());
            saveCourse.setImg(request.getImg());
            saveCourse.setTitle(request.getTitle());
            saveCourse.setDescription(request.getDescription());
            saveCourse.setStatus(request.getStatus());

        log.info("Category conversion successful");
        return saveCourse;
    }


}
