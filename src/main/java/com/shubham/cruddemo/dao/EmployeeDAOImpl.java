package com.shubham.cruddemo.dao;

import com.shubham.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository // indicates that this class is used to create, update, read and delete the objects
public class EmployeeDAOImpl implements EmployeeDAO{
    // define the field for Entity Manager
    private EntityManager entityManager;
    // set up the constructor injection
    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public List<Employee> findAll() {
        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
        // execute query and get the result
        // return the result
        return theQuery.getResultList();
    }

    @Override
    public Employee findById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        return employee;
    }

    @Override
    // when you are using the service layer, then let the service layer to manage the transactions. it is a good practice
    public Employee save(Employee employee) {
        Employee theEmployee = entityManager.merge(employee);
        return theEmployee;
    }

    @Override
    // when you are using the service layer, then let the service layer to manage the transactions. it is a good practice
    public void deleteById(int id) {
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
    }
}
