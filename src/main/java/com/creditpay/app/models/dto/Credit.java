package com.creditpay.app.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credit {
    private String id;
    private CreditCard creditCard;
    private Double amount;
    private LocalDateTime createAt;
}
