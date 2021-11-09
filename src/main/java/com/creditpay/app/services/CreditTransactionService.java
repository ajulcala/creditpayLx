package com.creditpay.app.services;

import com.creditpay.app.models.documents.CreditTransaction;
import com.creditpay.app.models.dto.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditTransactionService {
    Mono<CreditTransaction> create(CreditTransaction t);
    Flux<CreditTransaction> findAll();
    Mono<CreditTransaction> findById(String id);
    Mono<CreditTransaction> update(CreditTransaction t);
    Mono<Boolean> delete(String t);
    Flux<CreditTransaction> findCreditsPaid(String id);
    Mono<Credit> findCredit(String id);
}
