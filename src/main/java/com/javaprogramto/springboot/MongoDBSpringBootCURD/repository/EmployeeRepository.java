package com.javaprogramto.springboot.MongoDBSpringBootCURD.repository;

import com.javaprogramto.springboot.MongoDBSpringBootCURD.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

//@Component
public interface EmployeeRepository extends MongoRepository<Employee, Integer> {


}
