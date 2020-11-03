package com.agilethought.internship.university.controller;

import com.agilethought.internship.university.UniversityDummy;
import com.agilethought.internship.university.domain.CreateCourseRequest;
import com.agilethought.internship.university.domain.CreateCourseResponse;
import com.agilethought.internship.university.domain.UpdateCourseRequest;
import com.agilethought.internship.university.domain.UpdateCourseResponse;
import com.agilethought.internship.university.service.UniversityService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

public class UniversityControllerUnitTest {

    @Mock
    UniversityService universityService;

    @InjectMocks
    private UniversityController universityController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPostSuccessful() throws Exception {

        ResponseEntity<CreateCourseResponse> response = new ResponseEntity<>(new CreateCourseResponse(), HttpStatus.CREATED);
        Mockito.when(universityService.createCourse(Mockito.any(CreateCourseRequest.class)))
                .thenReturn(new CreateCourseResponse());

        ResponseEntity<CreateCourseResponse> result = universityController.postCourses(UniversityDummy.getPostChartNoteRequest());
        assertThat(result.getBody()).isEqualToComparingFieldByField(response.getBody());
    }
    

}
