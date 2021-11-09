package com.creditpay.app.services.impl;

import com.creditpay.app.models.dao.CreditTransactionDao;
import com.creditpay.app.models.documents.CreditTransaction;
import com.creditpay.app.models.dto.Credit;
import com.creditpay.app.services.CreditTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Slf4j
@Service
public class CreditTransactionServiceImpl implements CreditTransactionService {
    private final WebClient webClient;
    private final ReactiveCircuitBreaker reactiveCircuitBreaker;

    @Value("${config.base.apigatewey}")
    private String url;

    public CreditTransactionServiceImpl(ReactiveResilience4JCircuitBreakerFactory circuitBreakerFactory) {
        this.webClient = WebClient.builder().baseUrl(this.url).build();
        this.reactiveCircuitBreaker = circuitBreakerFactory.create("creditcharge");
    }

    @Autowired
    private CreditTransactionDao dao;

    @Override
    public Mono<Credit> findCredit(String id) {
        log.info("buscando creditcharge");
        return reactiveCircuitBreaker.run(webClient.get().uri(this.url,id).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Credit.class),
                throwable -> {
                    return this.getDefaultCreditCard();
                });
    }

    public Mono<Credit> getDefaultCreditCard() {
        log.info("no encontro el servicio");
        Mono<Credit> credit = Mono.just(new Credit("0", null, null,null));
        return credit;
    }

    @Override
    public Mono<CreditTransaction> create(CreditTransaction t) {
        return dao.save(t);
    }

    @Override
    public Flux<CreditTransaction> findAll() {
        return dao.findAll();
    }

    @Override
    public Mono<CreditTransaction> findById(String id) {
        return dao.findById(id);
    }

    @Override
    public Mono<CreditTransaction> update(CreditTransaction t) {
        return dao.save(t);
    }

    @Override
    public Mono<Boolean> delete(String t) {
        return dao.findById(t)
                .flatMap(tar -> dao.delete(tar).then(Mono.just(Boolean.TRUE)))
                .defaultIfEmpty(Boolean.FALSE);
    }

    @Override
    public Flux<CreditTransaction> findCreditsPaid(String id) {
        return dao.findByCreditId(id);
    }
}
