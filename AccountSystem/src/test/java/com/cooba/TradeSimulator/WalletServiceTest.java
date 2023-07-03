package com.cooba.TradeSimulator;

import com.cooba.TradeSimulator.DataAccess.WalletDataAccess;
import com.cooba.TradeSimulator.DataAccess.WalletDataAccessImp;
import com.cooba.TradeSimulator.Exception.InsufficientBalanceException;
import com.cooba.TradeSimulator.Object.Asset;
import com.cooba.TradeSimulator.Object.wallet.CurrencyWallet;
import com.cooba.TradeSimulator.Object.wallet.Wallet;
import com.cooba.TradeSimulator.Service.Interface.WalletService;
import com.cooba.TradeSimulator.Service.WalletServiceImpl;
import com.cooba.TradeSimulator.Util.RLock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Disabled
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {WalletServiceImpl.class, RLock.class, WalletDataAccessImp.class})
class WalletServiceTest {

    @Autowired
    WalletServiceImpl walletService;

    @Mock
    WalletDataAccess walletDataAccess;

    private Wallet wallet;

    @BeforeEach
    void setUp() {
        CurrencyWallet currencyWallet = new CurrencyWallet();

        Asset TWD = Asset.builder().type("TWD").amount(100).build();
        Asset CNY = Asset.builder().type("CNY").amount(100).build();
        Asset USD = Asset.builder().type("USD").amount(100).build();

        currencyWallet.setAssets(List.of(TWD, CNY, USD));
        wallet = currencyWallet;

        Mockito.doNothing().when(walletDataAccess).updateWallet(wallet);
    }

    @Test
    void deposit() {
        Asset TWD = Asset.builder().type("TWD").amount(100).build();
        walletService.deposit(wallet, TWD);

        Map<String, Asset> assets = wallet.getAssets();
        Map<String, Asset> expected = Map.of(
                        "TWD", Asset.builder().type("TWD").amount(200).build(),
                        "CNY", Asset.builder().type("CNY").amount(100).build(),
                        "USD", Asset.builder().type("USD").amount(100).build());
        System.out.println(assets);
        assert(expected.equals(assets)):"存款功能失敗";
    }

    @Test
    void withdraw() {
        Asset TWD = Asset.builder().type("TWD").amount(200).build();

        assertThrows(InsufficientBalanceException.class, () -> walletService.withdraw(wallet, TWD), "餘額不足仍可提款");
        Map<String, Asset> assets = wallet.getAssets();
        Map<String, Asset> expected = Map.of(
                "TWD", Asset.builder().type("TWD").amount(100).build(),
                "CNY", Asset.builder().type("CNY").amount(100).build(),
                "USD", Asset.builder().type("USD").amount(100).build());
        System.out.println(assets);
        assert(expected.equals(assets)):"提款餘額不足後帳戶金額異常";
    }

    @Test
    void showAsset() {
        walletService.showAsset(wallet);
        Map<String, Asset> assets = wallet.getAssets();
        Map<String, Asset> expected = Map.of(
                "TWD", Asset.builder().type("TWD").amount(100).build(),
                "CNY", Asset.builder().type("CNY").amount(100).build(),
                "USD", Asset.builder().type("USD").amount(100).build());
        assert(expected.equals(assets)):"帳戶金額異常";
    }
}