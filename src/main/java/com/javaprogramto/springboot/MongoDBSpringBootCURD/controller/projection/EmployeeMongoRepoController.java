package com.javaprogramto.springboot.MongoDBSpringBootCURD.controller.projection;

import com.javaprogramto.springboot.MongoDBSpringBootCURD.model.Employee;
import com.javaprogramto.springboot.MongoDBSpringBootCURD.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongorepo/projection")
public class EmployeeMongoRepoController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping("/nameExcludeId")
    public List<Employee> getAll() {

        List<Employee> list = employeeRepository.findNameAndExcludeId();

        return list;
    }

    @GetMapping("/nameage")
    public List<Employee> getNameAge() {

        List<Employee> list = employeeRepository.nameAndAge();

        return list;
    }

    @GetMapping("/idage")
    public List<Employee> getIdAge() {

        List<Employee> list = employeeRepository.findByIdAge();

        return list;
    }

    @GetMapping("/ids")
    public List<Employee> getOnlyIds() {

        List<Employee> list = employeeRepository.findOnlyIds();

        return list;
    }

    @GetMapping("/excludeid")
    public List<Employee> excludeId() {

        List<Employee> list = employeeRepository.excludeId();

        return list;
    }



}
