package com.cooba.TradeSimulator.Service;

import com.cooba.TradeSimulator.Service.Interface.TradeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StockTradeService implements TradeService {

    public void buy(Integer userId, Integer stockId, Integer currencyId, BigDecimal amount){
        getStockInfo();
        getCurrencyRate();
        payMoney();
        if(isPaySuccess()){
            payBack();
        }
        logTradeRecord();
    }

    public void sell(Integer userId, Integer stockId, Integer currencyId, BigDecimal amount){

    }

    private void getStockInfo(){

    }

    private void payMoney(){

    }

    private void getCurrencyRate(){

    }

    private boolean isPaySuccess(){
        return false;
    }

    private void payBack(){

    }

    private void logTradeRecord(){

    }
}
