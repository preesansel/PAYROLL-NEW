package com.payroll.feign;



import com.payroll.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "employee-service")
public interface EmployeeFeignClient {
    @GetMapping("/employees")
    List<EmployeeDto> getAllEmployees();
}
