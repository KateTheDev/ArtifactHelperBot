package com.company;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EarnBot extends TelegramLongPollingBot {
    private static final String Botname = "ES_Bot";
    private static final String Botttoken = "656140296:AAEr_grx__yJB8nBQhoyBsc2xFrlSH_sDgQ";
    private static final String Help = "Привет, я бот который поможет сэкономить Ваши деньги. Если Вы хотите что-то купить, то отправьте мне" +
            " название и модель товара, номер телефона, к которому привязан Ваш телеграм, а также email в таком формате:\n<номер телефона>" +
            "\n<название товара>\n<email>\n" +
            "Затем я отправлю Ваш запрос на обработку. Как только" +
            "\nВаш товар будет найден, с Вами свяжутся!\nПриятных покупок!";
    private static final String AdminId = "436046265";

    List<String> blackList;

    {

    }


    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        long chatid = update.getMessage().getChatId();
        String text = message.getText();
        SendMessage sendmessage = new SendMessage();
        if (Long.toString(update.getMessage().getChatId()) == AdminId) {
            if (message.getText().equalsIgnoreCase("block user")) {
                sendmessage.setText("Введите id юзера");
                sendmessage.setChatId(AdminId);
                checkExp(sendmessage);
                text = message.getText();
                sendmessage.setText(Boolean.toString(blockUser(text)));
                sendmessage.setChatId(AdminId);
                checkExp(sendmessage);
            }
        } else if (text.equalsIgnoreCase("/start") || text.equalsIgnoreCase("/help") || text.equalsIgnoreCase("start") || text.equalsIgnoreCase("help")) {
            sendmessage.setText(Help);
            sendmessage.setChatId(chatid);
            checkExp(sendmessage);
        } else if (checkOffer(text)) {
            sendmessage.setText(text + "\nChat ID: " + Long.toString(chatid));
            sendmessage.setChatId(AdminId);
            checkExp(sendmessage);
            text = "Ваш заказ принят, ожидайте";
            sendmessage.setText(text);
            sendmessage.setChatId(chatid);
            checkExp(sendmessage);
        } else {
            text = "Упс... Кажется, Вы совершили ошибку при вводе! Вот пример ввода:\n+38012345678\nIPhone 10\nvasya123@gmail.com";
            sendmessage.setText(text);
            sendmessage.setChatId(chatid);
            checkExp(sendmessage);
        }
    }

    public boolean checkOffer(String text) {
        String pattern = "(\\s*)?[+]?[0-9]{9,14}(\\s*)?\n" +
                "([^~]*)\n" +
                "(\\S*)[@](\\S*)[.](\\S*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(text);
        return m.find();
    }

    public boolean blockUser(String userId) {
        for (String f : blackList) {
            if (userId == f) {
                return false;
            }
        }
        blackList.add(userId);
        return true;
    }

    public void checkExp(SendMessage sendmessage) {
        try {
            execute(sendmessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return Botname;
    }

    @Override
    public String getBotToken() {
        return Botttoken;
    }
}