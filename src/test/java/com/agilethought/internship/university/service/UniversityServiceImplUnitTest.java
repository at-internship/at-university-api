package com.agilethought.internship.university.service;

import com.agilethought.internship.university.UniversityDummy;
import com.agilethought.internship.university.domain.CreateCourseRequest;
import com.agilethought.internship.university.domain.CreateCourseResponse;
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


}
