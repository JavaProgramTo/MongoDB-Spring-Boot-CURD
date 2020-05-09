package com.javaprogramto.springboot.MongoDBSpringBootCURD.controller.projection;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import com.javaprogramto.springboot.MongoDBSpringBootCURD.model.AgeCount;
import com.javaprogramto.springboot.MongoDBSpringBootCURD.model.Employee;
import com.javaprogramto.springboot.MongoDBSpringBootCURD.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/aggregation")
public class AggregationController {

    @Autowired
    private MongoTemplate mongoTemplate;


    @GetMapping("/group")
    public List<AgeCount> groupByAge() {

        MatchOperation empIdgt10 = Aggregation.match(Criteria.where("_id").gt(10));

        // grouping by age.
        GroupOperation groupOperation = Aggregation.group("age").count().as("count");

        ProjectionOperation projectionOperation = Aggregation.project("count").and("age").previousOperation();

        // filtering same age count > 1
        MatchOperation matchOperation = Aggregation.match(new Criteria("count").gt(1));


        SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.ASC, "count", "age"));

        Aggregation aggregation = Aggregation.newAggregation(empIdgt10, groupOperation, projectionOperation, matchOperation, sortOperation);


        AggregationResults<AgeCount> list = mongoTemplate.aggregate(aggregation, "Employee", AgeCount.class);


        return list.getMappedResults();
    }

    @GetMapping("/groupbysimples")
    public List<String> groupByAge2() {

        Aggregation agg = newAggregation(
                match(Criteria.where("_id").gt(10)),
                group("age").count().as("total"),
                project("total").and("age").previousOperation(),
                sort(Sort.Direction.ASC, "total")

        );

        //Convert the aggregation result into a List
        AggregationResults<String> groupResults = mongoTemplate.aggregate(agg, Employee.class, String.class);

        List<String> result = groupResults.getMappedResults();


        return result;
    }

}
