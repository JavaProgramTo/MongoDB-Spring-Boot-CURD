package com.javaprogramto.springboot.MongoDBSpringBootCURD.controller.projection;

import com.javaprogramto.springboot.MongoDBSpringBootCURD.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projection")
public class MongoTemplateProjectionController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/allname")
    public List<Employee> getOnlyName() {

        Query query = new Query();
        query.fields().include("name");
       List<Employee> list = mongoTemplate.find(query, Employee.class);

        return list;
    }

    @GetMapping("/allnameage")
    public List<Employee> getOnlyNameAge() {

        Query query = new Query();
        query.fields().include("name").include("age").exclude("id");
        List<Employee> list = mongoTemplate.find(query, Employee.class);

        return list;
    }

    @GetMapping("/excludename")
    public List<Employee> excludeName() {

        Query query = new Query();
        query.fields().exclude("name");
        List<Employee> list = mongoTemplate.find(query, Employee.class);

        return list;
    }

    @GetMapping("/excludeId")
    public List<Employee> excludeId() {

        Query query = new Query();
        query.fields().exclude("id");
        List<Employee> list = mongoTemplate.find(query, Employee.class);

        return list;
    }


}
