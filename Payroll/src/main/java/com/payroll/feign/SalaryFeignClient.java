package com.payroll.feign;


import com.payroll.dto.SalaryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "salary-service")
public interface SalaryFeignClient {
    @GetMapping("/salary")
    SalaryDto getSalaryDetails(@RequestParam("empId") Long empId);
}

