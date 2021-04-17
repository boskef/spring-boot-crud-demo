package com.kayhanozturk.springboot.cruddemo.dao;

import com.kayhanozturk.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> findAll();
    public Employee findById(int employeeId);
    public void save(Employee employee);
    public void delete(int employeeId);
}