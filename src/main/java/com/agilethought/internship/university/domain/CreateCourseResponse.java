package com.agilethought.internship.university.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public class CreateCourseResponse {
    @Id
    @ApiModelProperty(value = "Course id", example = "5fabf9ee11993d0719574a76")
    private String id;

}
