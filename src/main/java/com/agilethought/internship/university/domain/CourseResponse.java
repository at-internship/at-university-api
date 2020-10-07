package com.agilethought.internship.university.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class CourseResponse {
    @Id
    public String _id;
    private String category;
    private String title;
    private String description;
    private String img;
    private int status;

}
