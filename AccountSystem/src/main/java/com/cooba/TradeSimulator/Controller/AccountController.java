package com.cooba.TradeSimulator.Controller;

import com.cooba.TradeSimulator.Object.AccountDto;
import com.cooba.TradeSimulator.Service.Interface.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/{name}")
    public Mono<ResponseEntity> createAccount(@PathVariable String name) {
        return Mono.just(ResponseEntity.ok(accountService.createAccount(name)));
    }

    @PutMapping("/{uuid}/{name}")
    public Mono<ResponseEntity> updateAccountIfExist(@PathVariable String uuid, @PathVariable String name) {
        accountService.updateAccountIfExist(uuid, name);
        return Mono.just(ResponseEntity.ok().build());
    }

    @DeleteMapping("/{uuid}")
    public Mono<ResponseEntity> deleteAccountIfExist(@PathVariable String uuid) {
        accountService.deleteAccountIfExist(uuid);
        return Mono.just(ResponseEntity.ok().build());
    }

    @PostMapping("/{uuid}")
    public Mono<ResponseEntity> getAccount(@PathVariable String uuid) {
        Optional<AccountDto> accountDtoOptional = accountService.getAccount(uuid);
        return accountDtoOptional.<Mono<ResponseEntity>>map(accountDto -> Mono.just(ResponseEntity.ok(accountDto)))
                .orElseGet(() -> Mono.just(ResponseEntity.ok(AccountDto.builder().build())));
    }
}
