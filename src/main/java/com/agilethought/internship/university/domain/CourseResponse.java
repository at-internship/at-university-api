package com.agilethought.internship.university.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class CourseResponse {
    @Id
    @ApiModelProperty(value = "Course id", example = "5fabf9ee11993d0719574a76")
    public String _id;
    @ApiModelProperty(value = "Course category", example = "JAVA")
    private String category;
    @ApiModelProperty(value = "Course title", example = "Java 8 essentials")
    private String title;
    @ApiModelProperty(value = "Course description", example = "Course about Java 8 basics")
    private String description;
    @ApiModelProperty(value = "Logo of the Course", example = "img.jpg")
    private String img;
    @ApiModelProperty(value = "Course status", example = "1")
    private int status;

}
