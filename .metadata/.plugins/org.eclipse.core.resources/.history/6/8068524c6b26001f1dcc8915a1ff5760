package com.payroll.model;





import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "payroll")

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private Long payId;

    @Column(name = "emp_id", nullable = false)
    private Long empId;

    @Column(name = "pay_month", nullable = false)
    private Integer payMonth;

    @Column(name = "pay_year", nullable = false)
    private Integer payYear;

    @Column(name = "basic_pay", nullable = false)
    private Double basicPay;

    @Column(name = "house_rent_allowance", nullable = false)
    private Double houseRentAllowance;

    @Column(name = "special_allowance", nullable = false)
    private Double specialAllowance;

    @Column(name = "ytd_basic_pay", nullable = false)
    private Double ytdBasicPay;

    @Column(name = "ytd_house_rent_allowance", nullable = false)
    private Double ytdHouseRentAllowance;

    @Column(name = "ytd_special_allowance", nullable = false)
    private Double ytdSpecialAllowance;

    @Column(name = "provident_fund", nullable = false)
    private Double providentFund;

    @Column(name = "income_tax", nullable = false)
    private Double incomeTax;

    @Column(name = "professional_tax", nullable = false)
    private Double professionalTax;

    @Column(name = "ytd_provident_fund", nullable = false)
    private Double ytdProvidentFund;

    @Column(name = "ytd_income_tax", nullable = false)
    private Double ytdIncomeTax;

    @Column(name = "ytd_professional_tax", nullable = false)
    private Double ytdProfessionalTax;

    @Column(name = "lop", nullable = false)
    private Double lop;

    @Column(name = "gross_earnings", nullable = false)
    private Double grossEarnings;

    @Column(name = "gross_deductions", nullable = false)
    private Double grossDeductions;

    @Column(name = "net_pay", nullable = false)
    private Double netPay;

}

  

   

  

  

   
