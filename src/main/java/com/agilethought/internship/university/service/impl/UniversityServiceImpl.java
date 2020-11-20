package com.agilethought.internship.university.service.impl;

import com.agilethought.internship.university.domain.*;
import com.agilethought.internship.university.exception.NotFoundException;
import com.agilethought.internship.university.model.Course;
import com.agilethought.internship.university.repository.CoursesRepository;
import com.agilethought.internship.university.service.UniversityService;
import com.agilethought.internship.university.service.common.CategoryConstants;
import com.agilethought.internship.university.service.common.OrikaTransformer;
import com.agilethought.internship.university.validations.Validator;
import javafx.scene.transform.NonInvertibleTransformException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        log.info("UniversityServiceImpl.createCourse - Starting POST validations");
        Validator.requestValidation(request);
        log.info("UniversityServiceImpl.createCourse - POST validations completed");
        Course course = orikaTransformer.transformer(request);
        repository.save(course);
        log.info("UniversityServiceImpl.createCourse - Course saved with id: {}", course.get_id());
        response.setId(course.get_id().toString());
        log.info("UniversityServiceImpl.createCourse - createCourse operation was successful: {}", response);
        return response;
    }


    public List<CourseResponse> getCourses(String title) {

        List<Course> unchanged;
        log.info("UniversityServiceImpl.getCourses - Before getting all the courses");
        if(StringUtils.isNotBlank(title)){
            unchanged= repository.findCoursesByTitle(title);
        }else {
            unchanged= repository.findAll();
            if(unchanged.isEmpty())
                throw new NotFoundException("No courses available, try again later", "/course/");
        }
        log.info("UniversityServiceImpl.getCourses - After getting courses: {}", unchanged);
        List<CourseResponse> newList = new ArrayList<>();

        for (Course c:unchanged) {
            CourseResponse courseResponse = new CourseResponse();
            courseResponse = orikaTransformer.transformer(c);
            newList.add(courseResponse);
        }
        log.info("UniversityServiceImpl.getCourses - list of courses transformed: {}", newList);
        if (newList.isEmpty())
            throw new NotFoundException("No courses available, try again later","/course/");
        log.info("UniversityServiceImpl.getCourses - getCourses operation was successful: {}", newList);
        return newList;
    }


    public UpdateCourseResponse updateCourse(UpdateCourseRequest request, String courseid) {
        UpdateCourseResponse response = new UpdateCourseResponse();
        log.info("UniversityServiceImpl.updateCourse - Starting PUT validations");
        Validator.requestValidationToUpdate(request,courseid);
        log.info("UniversityServiceImpl.updateCourse - PUT validations completed");
        if (repository.existsById(courseid)) {
            log.info("UniversityServiceImpl.updateCourse - id exists");
            Optional<Course> existCourse = repository.findById(courseid);
            log.info("UniversityServiceImpl.updateCourse - id found");
            Course newCourse = requestToUpdate(request, existCourse.get());
            repository.save(newCourse);
            log.info("UniversityServiceImpl.updateCourse - Course saved successfully");
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
            log.info("UniversityServiceImpl.updateCourse - updateCourse operation was successful: {}", response);
            return response;
        } else
            throw new NotFoundException("Course not found, check id and try again","/course/{id}");
    }

    @Override
    public void deleteCourse(String id) {
        log.info("UniversityServiceImpl.deleteCourse - id received:{}",id);
        Validator.validationToDelete(id);
        if (repository.existsById(id)){
            log.info("UniversityServiceImpl.deleteCourse - id exists");
            repository.deleteById(id);
            log.info("UniversityServiceImpl.deleteCourse - Course deleted successfully");
        } else{
            log.warn("UniversityServiceImpl.deleteCourse - id not found");
            throw new NotFoundException("Course does not exist, check id and try again","/course/");
        }
    }

    public Course requestToUpdate(UpdateCourseRequest request, Course saveCourse){

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
        if (Objects.nonNull(request.getStatus()))
            saveCourse.setStatus(request.getStatus());

        log.info("UniversityServiceImpl.requestToUpdate - Category conversion successful");
        return saveCourse;
    }

}
