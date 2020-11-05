package com.agilethought.intership.university.university.service;

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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;

public class UniversityServiceImplUnitTest {



    @InjectMocks
    private UniversityServiceImpl universityService;

    @Mock
    OrikaTransformer orikaTransformer;

    @Mock
    CoursesRepository coursesRepository;

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
        List<CourseResponse> response = UniversityDummy.getListCourseResponseEmpty();

        Mockito.when(orikaTransformer.transformer(Mockito.any(Course.class)))
                .thenReturn(UniversityDummy.getCourseResponse());

        List<CourseResponse> result = universityService.getCourses();
        assertThat(result).isEqualTo(response);
    }

    @Test
    public void putCourseServiceSuccessful() throws Exception{
        UpdateCourseResponse response = UniversityDummy.getPutChartNoteResponse();

        Mockito.when(universityService.updateCourse(Mockito.any(UpdateCourseRequest.class),
                eq(UniversityDummy.getPathVariableId())))
                .thenReturn(UniversityDummy.getPutChartNoteResponse());

        UpdateCourseResponse result = universityService.updateCourse(
                UniversityDummy.getPutChartNoteRequest(),eq(UniversityDummy.getPathVariableId()));
        assertThat(result).isEqualToComparingFieldByField(response);
    }

    @Test
    public void putCourseToRequestServiceSuccessful() throws Exception{
        Course response = UniversityDummy.getCourse();

        Mockito.when(universityService.requestToUpdate(Mockito.any(UpdateCourseRequest.class)
                ,Mockito.any(Course.class)))
                .thenReturn(UniversityDummy.getCourseTest());

        Course result = universityService.requestToUpdate(
                UniversityDummy.getPutChartNoteRequest(),UniversityDummy.getCourse());
        assertThat(result).isEqualToComparingFieldByField(response);
    }

}
