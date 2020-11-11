package com.agilethought.internship.university.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateCourseRequest {

    @ApiModelProperty(value = "Course category", example = "JS")
    private String category;
    @ApiModelProperty(value = "Course title", example = "JS essentials")
    private String title;
    @ApiModelProperty(value = "Course description", example = "Course about JavaScript basics")
    private String description;
    @ApiModelProperty(value = "Logo of the Course", example = "img.jpg")
    private String img;
    @ApiModelProperty(value = "Course status", example = "2")
    private Integer status;

}
