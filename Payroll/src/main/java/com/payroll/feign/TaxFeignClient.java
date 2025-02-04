package com.payroll.feign;


import com.payroll.dto.TaxDeductionsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "tax-service")
public interface TaxFeignClient {
    @PostMapping("/tax/calculate")
    TaxDeductionsDto calculateTaxDeductions(@RequestBody Map<String, Double> salaryDetails);
}
