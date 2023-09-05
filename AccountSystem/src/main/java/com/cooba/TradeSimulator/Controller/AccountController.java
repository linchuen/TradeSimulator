package com.cooba.TradeSimulator.Controller;

import com.cooba.TradeSimulator.DataLayer.AccountDB;
import com.cooba.TradeSimulator.Entity.Account;
import com.cooba.TradeSimulator.Exception.InsufficientException;
import com.cooba.TradeSimulator.Exception.NotExistException;
import com.cooba.TradeSimulator.Exception.NotSupportCurrencyException;
import com.cooba.TradeSimulator.Object.AccountDto;
import com.cooba.TradeSimulator.Object.asset.CurrencyAsset;
import com.cooba.TradeSimulator.Service.Interface.AccountService;
import com.cooba.TradeSimulator.Service.Interface.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private AccountDB accountDB;

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

    @GetMapping("/{uuid}")
    public Mono<ResponseEntity<AccountDto>> getAccount(@PathVariable String uuid) throws NotExistException {
        Optional<AccountDto> accountDtoOptional = accountService.getAccount(uuid);
        return accountDtoOptional.map(accountDto -> Mono.just(ResponseEntity.ok(accountDto)))
                .orElseThrow(NotExistException::new);
    }

    @PostMapping("/deposit/{userId}/{currencyId}/{amount}")
    public Mono<ResponseEntity> deposit(@PathVariable Integer userId, @PathVariable Integer currencyId, @PathVariable BigDecimal amount)
            throws NotExistException {
        accountService.deposit(userId, currencyId, amount);
        return Mono.just(ResponseEntity.ok().build());
    }

    @PostMapping("/withdraw/{userId}/{currencyId}/{amount}")
    public Mono<ResponseEntity> withdraw(@PathVariable Integer userId, @PathVariable Integer currencyId, @PathVariable BigDecimal amount)
            throws NotExistException, InsufficientException {
        accountService.withdraw(userId, currencyId, amount);
        return Mono.just(ResponseEntity.ok().build());
    }

    @GetMapping("/asset/{userId}/{currencyId}")
    public Mono<ResponseEntity> assessAssetByUnit(@PathVariable Integer userId, @PathVariable Integer currencyId)
            throws NotSupportCurrencyException {
        CurrencyAsset currencyAsset = walletService.assessAssetByUnit(userId, currencyId);
        return Mono.just(ResponseEntity.ok(currencyAsset));
    }

    @GetMapping("/detail/{uuid}")
    public Mono<ResponseEntity<Account>> selectDetailAccount(@PathVariable String uuid) throws NotExistException {
        Optional<Account> accountOptional = accountDB.selectDetailAccount(uuid);
        return accountOptional.map(account -> Mono.just(ResponseEntity.ok(account)))
                .orElseThrow(NotExistException::new);
    }
}
