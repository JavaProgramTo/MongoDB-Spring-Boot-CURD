package com.javaprogramto.springboot.MongoDBSpringBootCURD.repository;

import com.javaprogramto.springboot.MongoDBSpringBootCURD.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeRepository extends MongoRepository<Employee, Integer> {


    // Getting only name and excluding id field.
    @Query(value = "{}", fields = "{name : 1,_id : 0}")
    public List<Employee> findNameAndExcludeId();

    // getting only name age fileds but id will be fetched automatically because it is annotated with @Id.
    @Query(value = "{}", fields = "{name : 1, age : 1}")
    public List<Employee> nameAndAge();

    // Fetches only Id.
    @Query(value = "{}", fields = "{id : 1}")
    public List<Employee> findOnlyIds();

    // Fetches only id and age.
    @Query(value = "{}", fields = "{id : 1, age : 1}")
    public List<Employee> findByIdAge();

    // Fetches only id and age.
    @Query(value = "{}", fields = "{id : 0}")
    public List<Employee> excludeId();
}
