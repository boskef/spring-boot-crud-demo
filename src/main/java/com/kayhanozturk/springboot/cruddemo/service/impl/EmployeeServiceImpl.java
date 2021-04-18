package com.kayhanozturk.springboot.cruddemo.service.impl;

import com.kayhanozturk.springboot.cruddemo.dao.EmployeeDAO;
import com.kayhanozturk.springboot.cruddemo.dao.impl.EmployeeDAOHibernateImpl;
import com.kayhanozturk.springboot.cruddemo.entity.Employee;
import com.kayhanozturk.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    //setup constructor injection
    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(@Qualifier("employeeDAOJPAImpl") EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    @Transactional
    public Employee findById(int employeeId) {
        return employeeDAO.findById(employeeId);
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(int employeeId) {
        employeeDAO.delete(employeeId);
    }
}
