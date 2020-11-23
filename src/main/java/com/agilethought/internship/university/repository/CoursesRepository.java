package com.agilethought.internship.university.repository;

import com.agilethought.internship.university.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends MongoRepository<Course,String> {

    List<Course> findCoursesByTitle(String title);

}
