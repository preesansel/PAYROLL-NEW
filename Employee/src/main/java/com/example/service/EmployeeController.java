package com.example.service;






import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @GetMapping("/employees")
    public List<Long> getEmployeeIds() {
        // Dummy employee IDs
        List<Long> employeeIds = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            employeeIds.add((long) (110 + i)); // Generating employee IDs like 111, 112, 113, ...
        }
        return employeeIds;
    }
}

