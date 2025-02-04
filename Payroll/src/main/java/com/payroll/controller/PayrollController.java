package com.payroll.controller;


import com.payroll.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @GetMapping("/process")
    public String processPayroll() {
        // Trigger the payroll processing logic
        payrollService.processPayroll();
        return "Payroll processing initiated";
    }
}
