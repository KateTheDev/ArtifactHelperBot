package com.company;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME ="root";
    private static final String PASSWORD ="loginpassword228";

    public static void main(String[] args) {
        /*onnection connection;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                System.out.println("Соединение с БД установлено");
            }
        }catch (SQLException e){
            System.out.println("Error");
        }*/

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot( new EarnBot());
        }catch(TelegramApiRequestException e){
            e.printStackTrace();
        }
    }
}
