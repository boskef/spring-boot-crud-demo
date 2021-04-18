package com.kayhanozturk.springboot.cruddemo.dao.impl;

import com.kayhanozturk.springboot.cruddemo.dao.EmployeeDAO;
import com.kayhanozturk.springboot.cruddemo.entity.Employee;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeDAOJPAImpl implements EmployeeDAO {
    //define field for entityManagers
    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        //create a query
        TypedQuery<Employee> typedQuery = entityManager.createQuery("from Employee",Employee.class);
        //execute a query
        List<Employee> employeeList = typedQuery.getResultList();
        //return the results
        return employeeList;
    }

    @Override
    public Employee findById(int employeeId) {

        //get employee
        Employee employee = entityManager.find(Employee.class,employeeId);

        //return the employee
        return employee;
    }

    @Override
    public void save(Employee employee) {

        //save or update the employee
        Employee theEmployee = entityManager.merge(employee);

        //update with id from db.. we can generate id for save/insert.
        employee.setId(theEmployee.getId());
    }

    @Override
    public void delete(int employeeId) {

        //delete object with primaryKey
        Query query = (Query) entityManager.createQuery("delete from Employee where id=:employeeId");

        query.setParameter("employeeId",employeeId);

        query.executeUpdate();
    }
}
