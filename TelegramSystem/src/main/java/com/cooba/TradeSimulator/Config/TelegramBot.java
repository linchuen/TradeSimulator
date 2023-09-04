package com.cooba.TradeSimulator.Config;


import com.cooba.TradeSimulator.Service.BotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.Method;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${telegram.token}")
    private String token;
    @Value("${telegram.token}")
    private String bot_name;
    @Value("${telegram.chatID}")
    private String chatID;
    @Autowired
    BotService botService;

    @Override
    public String getBotUsername() {
        return this.bot_name;
    }

    @Override
    public String getBotToken() {
        return this.token;
    }

    public String getChatID() {
        return this.chatID;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            String[] msgGroup = update.getMessage().getText().split(" ");
            String command = msgGroup[0];
            String message = msgGroup.length > 1 ? msgGroup[1] : "";
            String response = botService.getMessageFromUser(command, message);

            SendMessage sendMessage = SendMessage.builder()
                    .text(response)
                    .chatId(chatID)
                    .build();
            execute(sendMessage);
        } catch (Exception e) {
            log.error("{} {} Exception on telegram update ", getClass().getSimpleName(), e.getMessage());
        }
    }
}
