package com.creditpay.app.models.documents;

import com.creditpay.app.models.dto.Credit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@Builder
@Document("credit_transaction")
@AllArgsConstructor
@NoArgsConstructor
public class CreditTransaction {
    @Id
    private String id;
    @NotNull
    private Credit credit;
    @NotBlank
    private String transactionCode;
    @NotNull
    private Double transactionAmount;
    private LocalDateTime transactionDate;
}
