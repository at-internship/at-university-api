package com.university.university.service;

import com.university.university.model.Course;
import com.university.university.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class AtUniversityServiceImpl implements AtUniversityService{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();

        courses = courseRepository.findAll();
        return courses;
    }

}
