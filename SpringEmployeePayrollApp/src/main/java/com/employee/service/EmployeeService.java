package com.employee.service;


import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private final List<Employee> employeeList = new ArrayList<>();

    public Employee createEmployee(Employee employee) {
        long newId = employeeList.isEmpty() ? 1 : employeeList.get(employeeList.size() - 1).getId() + 1;
        employee.setId(newId);
        employeeList.add(employee);
        return employee;
    }


    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeList.stream().filter(employee -> employee.getId().equals(id)).findFirst();
    }

    //update
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> existingEmployee = getEmployeeById(id);
        if (existingEmployee.isPresent()) {
            Employee employee = existingEmployee.get();
            employee.setName(updatedEmployee.getName());
            employee.setSalary(updatedEmployee.getSalary());
            return employee;
        } else {
            throw new RuntimeException("Employee not found");
        }
    }

    public void deleteEmployee(Long id) {
   employeeList.removeIf(employee -> employee.getId().equals(id));
    }
}
