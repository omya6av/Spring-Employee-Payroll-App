package com.employee.controller;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    //UC1  // UC2
    // GET all employees
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Optional<Employee> employee = employeeRepository.findById(id);

        // If employee is found, return the employee data with HTTP 200 OK
        if (employee.isPresent()) {
              return ResponseEntity.ok(employee.get());
        }else{
            //if not found
            return ResponseEntity.notFound().build();
        }
    }

//    Add new employee
    @PostMapping("/create")
    public Employee CreateEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // PUT - Update an existing employee
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(updatedEmployee.getName());
            employee.setRole(updatedEmployee.getRole());
            return ResponseEntity.ok(employeeRepository.save(employee));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE - Remove an employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        if(employeeRepository.existsById((id))){
            employeeRepository.deleteById(id);
            return ResponseEntity.ok("Employee with ID " + id + " deleted successfully.");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //UC2
    private final EmployeeRepository employeeRepository;

    //constructor based dependency injection
    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }



}
