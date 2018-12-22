package com.company;

import com.mongodb.DB;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


public class CardBot extends TelegramLongPollingBot {
    private static final String Botname = "ArtifactHelper";
    private static final String Botttoken = "697142603:AAFF2YlzfbmAdMLwTsvbEYcSp9UF2oWvfIA";


    @Override
    public void onUpdateReceived(Update update) {
        User user = new User();
        String chatid = update.getMessage().getChatId().toString();
        user.checkMessage(update.getMessage().getText(),chatid);
    }

    public void sendMsg(String text, String chatid) {
            SendMessage sendmessage = new SendMessage().setChatId(chatid).setText(text);
            buttons(sendmessage);
            try {
                execute(sendmessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
    }
    public void buttons(SendMessage sendMessage){

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Give me a random deck"));

        keyboardFirstRow.add(new KeyboardButton("Give me all the decks"));

        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardThirdRow.add(new KeyboardButton("The latest news"));

        keyboardThirdRow.add(new KeyboardButton("The latest 3 news"));


        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardThirdRow);


        replyKeyboardMarkup.setKeyboard(keyboard);
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