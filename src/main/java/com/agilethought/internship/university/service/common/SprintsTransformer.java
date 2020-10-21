package com.agilethought.internship.university.service.common;

import java.util.List;

import com.agilethought.internship.university.domain.CourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agilethought.internship.university.domain.CreateCourseRequest;
import com.agilethought.internship.university.model.Course;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
public class SprintsTransformer extends ConfigurableMapper{

    private MapperFacade mapperFacade;

    @Autowired
    public void setMapperFacade(MapperFactory mapperFactory){
        this.mapperFacade = mapperFactory.getMapperFacade();

        mapperFactory.classMap(CreateCourseRequest.class, Course.class)
                .mapNulls(false).mapNullsInReverse(false)
                .exclude("category")
                .byDefault()
                .register();

        mapperFactory.classMap(Course.class, CourseResponse.class)
                .mapNulls(false).mapNullsInReverse(false)
                .exclude("category")
                .byDefault()
                .register();
    }

    public Course transformer(CreateCourseRequest request){
        Course course = mapperFacade.map(request, Course.class);
        course.setCategory(CategoryConstants.valueOf(request.getCategory().toUpperCase()).getOrd());
        return course;
    }

    public List<CourseResponse> listTransformer(List<Course>courses){

        return mapperFacade.mapAsList(courses, CourseResponse.class);
    }

}
