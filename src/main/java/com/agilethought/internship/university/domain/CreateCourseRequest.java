package com.agilethought.internship.university.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateCourseRequest {

    private String category;
    private String title;
    private String description;
    private String img;
    private int status;

}
