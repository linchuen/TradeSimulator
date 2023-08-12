package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.DataLayer.AccountDataAccess;
import com.cooba.TradeSimulator.Entity.Account;
import com.cooba.TradeSimulator.Object.AccountDto;
import com.cooba.TradeSimulator.Service.Interface.AccountService;
import com.cooba.TradeSimulator.Service.Interface.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserAccountService implements AccountService {
    @Autowired
    private AccountDataAccess accountDataAccess;
    @Autowired
    private WalletService walletService;

    @Override
    public String createAccount(String name) {
        String uuid=UUID.randomUUID().toString();
        Account account = Account.builder()
                .name(name)
                .uuid(uuid)
                .build();
        accountDataAccess.insertAccount(account);
        return uuid;
    }

    @Override
    public void updateAccountIfExist(String uuid, String name) {
        Optional<Account> accountOptional = accountDataAccess.selectAccount(uuid);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setName(name);
            accountDataAccess.updateAccount(account);
        }
    }

    @Override
    public void deleteAccountIfExist(String uuid) {
        Optional<Account> accountOptional = accountDataAccess.selectAccount(uuid);
        accountOptional.ifPresent(account -> accountDataAccess.deleteAccount(uuid));
    }

    @Override
    public Optional<AccountDto> getAccount(String uuid) {
        Optional<Account> accountOptional = accountDataAccess.selectAccount(uuid);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return Optional.of(AccountDto.builder()
                    .uuid(account.getUuid())
                    .name(account.getName())
                    .password(account.getPassword())
                    .wallets(walletService.getWallets(account.getId()))
                    .build());
        }
        return Optional.empty();
    }
}
