package com.skibnev.telegrambot.newstelegrambot;

import com.skibnev.telegrambot.newstelegrambot.service.ServiceRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Controller
public class NewsBot extends TelegramLongPollingBot {

    @Autowired
    private ServiceRunner serviceRunner;

    @Override
    public String getBotUsername() {
        return "currencyExchangeUA_bot";
    }

    @Override
    public String getBotToken() {
        return "14----8";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            if (messageText != null && !messageText.equals("")) {
                if (messageText.equals("/start")) {
                    sendMsg(update.getMessage().getChatId().toString(), "Please, enter your city name");
                } else {
                    String message = serviceRunner.run(messageText).block();
                    sendMsg(update.getMessage().getChatId().toString(), message);
                }
            }
        }
    }

    public synchronized void sendMsg(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        }catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
