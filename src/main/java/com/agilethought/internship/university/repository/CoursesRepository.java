package com.agilethought.internship.university.repository;

import com.agilethought.internship.university.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends MongoRepository<Course,String> {

}
