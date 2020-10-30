package com.agilethought.internship.university.service.impl;

import com.agilethought.internship.university.domain.*;
import com.agilethought.internship.university.exception.NotFoundException;
import com.agilethought.internship.university.model.Course;
import com.agilethought.internship.university.repository.CoursesRepository;
import com.agilethought.internship.university.service.UniversityService;
import com.agilethought.internship.university.service.common.CategoryConstants;
import com.agilethought.internship.university.service.common.OrikaTransformer;
import com.agilethought.internship.university.validations.Validator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
        log.info("Starting POST validations");
        Validator.requestValidation(request);
        log.info("POST validations completed");
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
        if (newList.isEmpty())
            throw new NotFoundException("No records in DB, try again later","/courses/");
        return newList;
    }

    public UpdateCourseResponse updateCourse(UpdateCourseRequest request, String courseid) {

        UpdateCourseResponse response = new UpdateCourseResponse();
        Optional<Course> existCourse = repository.findById(courseid);

        log.info("Starting PUT validations");
        Validator.requestValidationToUpdate(request);
        log.info("PUT validations completed");
        if (repository.existsById(courseid)) {
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
            return response;
        } else
            throw new NotFoundException("Course not found, check id and try again","/courses/{id}");
    }

    @Override
    public void deleteCourse(String id) {
        log.info("id received:{}",id);
        if (repository.existsById(id)){
            log.info("id exists");
            repository.deleteById(id);
            log.info("id deleted");
        } else{
            //TODO IMPLEMENT NOT FOUND EXCEPTION
            log.warn("id not found");
        }
    }

    private Course requestToUpdate(UpdateCourseRequest request, Course saveCourse){

        if (StringUtils.isNotBlank(request.getCategory()))
            saveCourse.setCategory(CategoryConstants.valueOf(request.getCategory().toUpperCase()).getOrd());
        else
            saveCourse.setCategory(0);
        if (StringUtils.isNotBlank(request.getImg()))
            saveCourse.setImg(request.getImg());
        else
            saveCourse.setImg(null);
        if (StringUtils.isNotBlank(request.getTitle()))
            saveCourse.setTitle(request.getTitle());
        else
            saveCourse.setTitle(null);
        if (StringUtils.isNotBlank(request.getDescription()))
            saveCourse.setDescription(request.getDescription());
        else
            saveCourse.setDescription(null);

        log.info("Category conversion successful");
        return saveCourse;
    }

}
