package com.agilethought.intership.university.university.service;

import com.agilethought.internship.university.validations.Validator;
import com.agilethought.intership.university.university.UniversityDummy;
import com.agilethought.internship.university.domain.*;
import com.agilethought.internship.university.model.Course;
import com.agilethought.internship.university.repository.CoursesRepository;
import com.agilethought.internship.university.service.common.OrikaTransformer;
import com.agilethought.internship.university.service.impl.UniversityServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;

public class UniversityServiceImplUnitTest {



    @InjectMocks
    private UniversityServiceImpl universityService;

    @Mock
    OrikaTransformer orikaTransformer;

    @Mock
    CoursesRepository coursesRepository;

    @Mock
    Validator validator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void postCourseServiceSuccessful() throws Exception {
        CreateCourseResponse response = UniversityDummy.getPostChartNoteResponse();

        Mockito.when(orikaTransformer.transformer(Mockito.any(CreateCourseRequest.class)))
                .thenReturn(UniversityDummy.getCourse());

        CreateCourseResponse result = universityService.createCourse(UniversityDummy.getPostChartNoteRequest());
        assertThat(result).isEqualToComparingFieldByField(response);
    }

    @Test
    public void getCourseServiceSuccessful() throws Exception{

        List<CourseResponse> response = Collections.singletonList(new CourseResponse());
        Mockito.when(orikaTransformer.transformer(Mockito.any(Course.class)))
                .thenReturn(new CourseResponse());
        Mockito.when(coursesRepository.findAll())
                .thenReturn(new ArrayList<>(Collections.singletonList(new Course())));
        List<CourseResponse> result = universityService.getCourses(null);
        assertThat(result.get(0)).isEqualToComparingFieldByField(response.get(0));
    }

    @Test
    public void putCourseServiceSuccessful() throws Exception{

        Mockito.when(coursesRepository.existsById(Mockito.anyString()))
                .thenReturn(true);

        Mockito.when(coursesRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.of(new Course()));

        UpdateCourseResponse response = UniversityDummy.getPutChartNoteResponse();

        UpdateCourseResponse result = universityService.updateCourse(
                UniversityDummy.getPutChartNoteRequest(),UniversityDummy.getPathVariableId());
        assertThat(result).isEqualToComparingFieldByField(response);
    }

    @Test
    public void putCourseToRequestServiceSuccessful() throws Exception{
        Course response = UniversityDummy.getCourseTest();

        Course result = universityService.requestToUpdate(
                UniversityDummy.getPutChartNoteRequest(), new Course());
        assertThat(result).isEqualToComparingFieldByField(response);
    }

}
