package com.javaprogramto.springboot.MongoDBSpringBootCURD.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@ToString
@Document(collection = "Employee")
public class Employee {

    @Id
    private int id;
    private String name;
    private int age;
    private long phoneNumber;
    private Date dateOfJoin;


}
