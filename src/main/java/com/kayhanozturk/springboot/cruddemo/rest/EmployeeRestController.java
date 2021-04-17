package com.kayhanozturk.springboot.cruddemo.rest;

import com.kayhanozturk.springboot.cruddemo.dao.EmployeeDAO;
import com.kayhanozturk.springboot.cruddemo.entity.Employee;
import com.kayhanozturk.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    //quick and dirty: inject employeeDAO
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    //expose "employeees" and return list of employees.

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);

        if(employee == null){
            throw new RuntimeException("Employee Id not found - " + employeeId);
        }
        return employee;
    }

    //add mapping for POST /employees/
    @PostMapping("/employees")
    public Employee postEmployee(@RequestBody Employee employee){

      //  employee.setId(20);

        employeeService.save(employee);

        return employee;
    }

    //add mapping for PUT /employees - update existing employee informations.
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){

        employeeService.save(employee);

        return employee;
    }

    //add mapping for DELETE /employees/{employeeId} -- delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

        Employee employee = employeeService.findById(employeeId);

        if(employee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }
}
