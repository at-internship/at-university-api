package com.agilethought.internship.university.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Setter
@Getter
@ToString
public class UpdateCourseResponse {

    private String category;
    private String title;
    private String description;
    private String img;
    private int status;


}
