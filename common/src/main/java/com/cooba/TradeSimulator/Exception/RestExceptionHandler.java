package com.cooba.TradeSimulator.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@Slf4j
class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity> handleException(Exception ex) {
        return Mono.just(ResponseEntity.internalServerError().body(ex.getMessage()));
    }

}
