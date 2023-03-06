package com.example.Shop.controller;

import com.example.Shop.models.Employee;
import com.example.Shop.models.Role;
import com.example.Shop.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Employee employee, Model model){
        Employee userFromDB = employeeRepository.findByUsername(employee.getUsername());
        if(userFromDB != null){
            model.addAttribute("message", "Такой пользователь уже существует");
            return "registration";
        }
        employee.setActive(true);
        employee.setRoles(Collections.singleton(Role.USER));
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
        return "redirect:/login";
    }
}