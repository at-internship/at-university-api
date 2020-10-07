package com.agilethought.internship.university.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Setter
@ToString
@Document(collection = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String _id;
    private int category;
    private String title;
    private String description;
    private String img;
    private int status;

}
