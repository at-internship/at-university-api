package com.agilethought.internship.university.controller;

import com.agilethought.internship.university.UniversityDummy;
import com.agilethought.internship.university.domain.*;
import com.agilethought.internship.university.service.UniversityService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;

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

        ResponseEntity<CreateCourseResponse> response = new ResponseEntity<>(
                new CreateCourseResponse(), HttpStatus.CREATED);
        Mockito.when(universityService.createCourse(Mockito.any(CreateCourseRequest.class)))
                .thenReturn(new CreateCourseResponse());

        ResponseEntity<CreateCourseResponse> result = universityController.postCourses(
                UniversityDummy.getPostChartNoteRequest());
        assertThat(result.getBody()).isEqualToComparingFieldByField(response.getBody());
    }


    @Test
    public void testGetSuccessful() throws Exception {
        List<CourseResponse> response = new ArrayList<>();
        Mockito.when(universityService.getCourses()).thenReturn(UniversityDummy.getListCourseResponseEmpty());
        List<CourseResponse> result = universityController.getOperation();
        assertThat(result).isEqualTo(response);
    }

    @Test
    public void testPutSuccessful() throws Exception {

        ResponseEntity<UpdateCourseResponse> response = new ResponseEntity<>(
                new UpdateCourseResponse(), HttpStatus.OK);
        Mockito.when(universityService.updateCourse(Mockito.any(UpdateCourseRequest.class),eq("String id")))
                .thenReturn(new UpdateCourseResponse());

        ResponseEntity<UpdateCourseResponse> result = universityController.putOperation(
                UniversityDummy.getPutChartNoteRequest(),"test");
        assertThat(result.getBody()).isEqualToComparingFieldByField(response.getBody());
    }

}
