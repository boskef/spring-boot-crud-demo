package com.kayhanozturk.springboot.cruddemo.dao.impl;

import com.kayhanozturk.springboot.cruddemo.dao.EmployeeDAO;
import com.kayhanozturk.springboot.cruddemo.entity.Employee;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    //define field for entitymanager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public List<Employee> findAll() {

        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //create a query
        Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
        //execute a query and get result list
        List<Employee> employees = theQuery.getResultList();
        //return the results.
        return employees;
    }

    @Override
    public Employee findById(int employeeId) {

        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //get the employee
        Employee employee = currentSession.get(Employee.class,employeeId);
        //return the employee
        return employee;
    }

    @Override
    public void save(Employee employee) {
        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //save employee
        currentSession.saveOrUpdate(employee);
    }

    @Override
    public void delete(int employeeId) {
        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        //delete employee
        Query query = currentSession.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId",employeeId);
        query.executeUpdate();
    }
}
