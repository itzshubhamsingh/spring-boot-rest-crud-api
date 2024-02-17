package com.shubham.cruddemo.rest;

import com.shubham.cruddemo.dao.EmployeeDAO;
import com.shubham.cruddemo.entity.Employee;
import com.shubham.cruddemo.service.EmployeeService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    // inject employeeService
    @Autowired
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    // expose "/employees" and return all the employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // expose "/employees{employeeId}" and return the detail
    @GetMapping("/employees/{employeeId}")
    public Employee findByID(@PathVariable int employeeId){
        // do remember that the {studentID} @GetMapping and studentID in @PathVariable should be same.
        // means their spelling should be same.........end
        Employee employee =  employeeService.findById(employeeId);
        if(employee == null)
            throw new RuntimeException("Employee id not found - " + employeeId);
        return employee;
    }

    // add mapping for POST/employee - add new employee

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        // also just in case they pass an id in JSON ... set the id to 0
        // this is to force a save of new item instead of update
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // add mapping for PUT /employee - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee theEmployee = employeeService.save(employee);
        return theEmployee;
    }

    // add mapping for deleting the employee by id

    @DeleteMapping("/employees/{id}")
    public String deleteEmployeeById(@PathVariable int id){
        Employee employee = employeeService.findById(id);
        if(employee == null)
            throw new RuntimeException("Employee id not found - " + id);
        employeeService.deleteById(id);
        return "Employee Deleted";
    }
}
