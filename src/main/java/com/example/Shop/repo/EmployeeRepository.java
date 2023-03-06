package com.example.Shop.repo;

import com.example.Shop.models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findByUsername(String username);
    List<Employee> findByFirstNameContaining(String firstName);
}
