package com.employee.service;


import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        if (employee.getName() == null || employee.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee name is required");
        }
        if (employee.getSalary() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Salary must be greater than 0");
        }
        return employeeRepository.save(employee);
    }


    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee){
        return employeeRepository.findById(id).map(
                employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setRole(updatedEmployee.getRole());
                    employee.setSalary(updatedEmployee.getSalary());
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new RuntimeException("Employee not found!"));
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

}
