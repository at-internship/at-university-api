package com.university.university.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "courses")
public class Course {

    @Id
    private String _id;
    private Integer category;
    private String title;
    private String description;
    private String img;
    private Integer status;
}
