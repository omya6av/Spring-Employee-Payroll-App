package com.employee.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double salary;

    public Employee() {}

    public Employee(String name, double salary) {
        this.salary = salary;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }



}
