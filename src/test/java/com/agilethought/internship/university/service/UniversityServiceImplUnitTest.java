package com.agilethought.internship.university.service;

import com.agilethought.internship.university.UniversityDummy;
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

    /*@Test
    public void putCourseServiceSuccessful() throws Exception{
        UpdateCourseResponse response = UniversityDummy.getPutChartNoteRequest();

        Mockito.when(universityService.updateCourse(Mockito.any(UpdateCourseRequest.class)),eq("test"));
    }*/
}
