package com.kayhanozturk.springboot.cruddemo.service;

import com.kayhanozturk.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAll();

    public Employee findById(int employeeId);

    public void save(Employee employee);

    public void deleteById(int employeeId);
}
