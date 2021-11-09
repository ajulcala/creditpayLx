package com.creditpay.app.models.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class CreditCard {
    private String id;
    private String cardNumber;
    private Customer customer;
    private Double limitCredit;
    private LocalDate expiration;
    private LocalDateTime createAt;
}
