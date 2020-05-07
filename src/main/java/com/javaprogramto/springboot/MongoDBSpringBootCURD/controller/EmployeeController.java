package com.javaprogramto.springboot.MongoDBSpringBootCURD.controller;

import com.javaprogramto.springboot.MongoDBSpringBootCURD.model.Employee;
import com.javaprogramto.springboot.MongoDBSpringBootCURD.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/add")
    public String saveEmployee(@RequestBody Employee e) {

        employeeRepository.insert(e);

        return "Added Employee id " + e.getId();
    }

    @GetMapping("/find/{id}")
    public Optional<Employee> getEmployee(@PathVariable int id) {

        logger.info("find employee by id : "+id);
        Optional<Employee> emp = employeeRepository.findById(id);

        return emp;
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployee() {

        List<Employee> allEmployees = employeeRepository.findAll();

        return allEmployees;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {

        employeeRepository.deleteById(id);

        return "Delete emp for id " + id;
    }

    @PutMapping("/update")
    public String updateEmployee(@RequestBody Employee e) {

        employeeRepository.save(e);

        return "Updated Employee for id " + e.getId();
    }
}
