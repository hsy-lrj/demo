package com.hsy.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Document(collection = "student")
@Data
public class Student {
    @Id
    private String id;
    private String name;
    private Integer age;
    private String address;
    private List<String> hobby;
    private School school;
}
