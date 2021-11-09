package com.creditpay.app.models.dao;

import com.creditpay.app.models.documents.CreditTransaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface CreditTransactionDao extends ReactiveMongoRepository<CreditTransaction, String> {
    Flux<CreditTransaction> findByCreditId(String id);
}
