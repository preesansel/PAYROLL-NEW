package com.payroll.service;

import com.payroll.dto.EmployeeDto;
import com.payroll.dto.SalaryDto;
import com.payroll.dto.TaxDeductionsDto;
import com.payroll.feign.EmployeeFeignClient;
import com.payroll.feign.SalaryFeignClient;
import com.payroll.feign.TaxFeignClient;
import com.payroll.model.Payroll;
import com.payroll.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayrollService {

    @Autowired
    private EmployeeFeignClient employeeFeignClient;

    @Autowired
    private SalaryFeignClient salaryFeignClient;

    @Autowired
    private TaxFeignClient taxFeignClient;

    @Autowired
    private PayrollRepository payrollRepository;

//    @Scheduled(cron = "0 0 0 1 * ?") // This will run at midnight on the first day of every month
    public void processPayroll() {
        List<EmployeeDto> employees = employeeFeignClient.getAllEmployees();

        for (EmployeeDto employee : employees) {
            SalaryDto salary = salaryFeignClient.getSalaryDetails(employee.getEmpId());

            Map<String, Double> salaryDetails = new HashMap<>();
            salaryDetails.put("basicPay", salary.getBasicPay());
            salaryDetails.put("houseRentAllowance", salary.getHouseRentAllowance());
            salaryDetails.put("specialAllowance", salary.getSpecialAllowance());

            TaxDeductionsDto taxDeductions = taxFeignClient.calculateTaxDeductions(salaryDetails);

            Payroll payroll = payrollRepository.findByEmpId(employee.getEmpId()).orElse(new Payroll());
            payroll.setEmpId(employee.getEmpId());
            payroll.setPayMonth(LocalDate.now().getMonthValue());
            payroll.setPayYear(LocalDate.now().getYear());
            payroll.setBasicPay(salary.getBasicPay());
            payroll.setHouseRentAllowance(salary.getHouseRentAllowance());
            payroll.setSpecialAllowance(salary.getSpecialAllowance());
            payroll.setProvidentFund(salary.getProvidentFund());
            payroll.setProfessionalTax(taxDeductions.getProfessionalTax());
            payroll.setIncomeTax(taxDeductions.getIncomeTax());

            // Calculate net pay
            Double netPay = calculateNetPay(salary, taxDeductions, payroll.getLop());
            payroll.setNetPay(netPay);

            payrollRepository.save(payroll);
        }
    }

    private Double calculateNetPay(SalaryDto salary, TaxDeductionsDto taxDeductions, Double lop) {
        YearMonth yearMonth = YearMonth.now();
        int totalDaysInMonth = yearMonth.lengthOfMonth();
        Double dailyBasicPay = salary.getBasicPay() / totalDaysInMonth;
        Double dailyHra = salary.getHouseRentAllowance() / totalDaysInMonth;
        Double dailySpecialAllowance = salary.getSpecialAllowance() / totalDaysInMonth;

        // Number of days worked
        int daysWorked = totalDaysInMonth - lop.intValue();

        Double grossEarnings = dailyBasicPay * daysWorked + dailyHra * daysWorked + dailySpecialAllowance * daysWorked;
        Double grossDeductions = salary.getProvidentFund() + taxDeductions.getProfessionalTax() + taxDeductions.getIncomeTax();

        return grossEarnings - grossDeductions;
    }
}
