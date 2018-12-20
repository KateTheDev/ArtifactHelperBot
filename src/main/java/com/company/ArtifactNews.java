package com.company;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import static com.company.ArtifactCards.cutter;

class Newsitems {
    private String title;
    private String contents;
    private int date;

    public int getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}

class Appnews {
    private Appnews appnews;
    private List<Newsitems> newsitems;

    public List<Newsitems> getNewsitems() {
        return newsitems;
    }

    public Appnews getAppnews() {
        return appnews;
    }
}

public class ArtifactNews {
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Appnews appnews;

    void printNews(String chatid) {
        Request r = new Request.Builder()
                .url("http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=583950&count=3&key=0CBA96E33248359124100C4EC23EAC7E&format=json")
                .get()
                .build();
        Call newsCall = okHttpClient.newCall(r);
        try {
            CardBot mess = new CardBot();
            Response response = newsCall.execute();
            ResponseBody body = response.body();
            String bodystring = body.string();
            ArtifactNews news = new Gson().fromJson(bodystring, ArtifactNews.class);
            mess.sendMsg("\nDate: "+parseDate(news.appnews.getNewsitems().get(0).getDate())+"\n" +
                    news.appnews.getNewsitems().get(0).getTitle() + "\n\n" + cutter((cutter(news.appnews.getNewsitems().get(0).getContents(),'&',';')),'<', '>'), chatid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void printThreeNews(String chatid){
        Request r = new Request.Builder()
                .url("http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=583950&count=3&key=0CBA96E33248359124100C4EC23EAC7E&format=json")
                .get()
                .build();
        Call newsCall = okHttpClient.newCall(r);
        try {
            CardBot mess = new CardBot();
            Response response = newsCall.execute();
            ResponseBody body = response.body();
            String bodystring = body.string();
            ArtifactNews news = new Gson().fromJson(bodystring, ArtifactNews.class);
            for(int i=0;i<3;i++)
            mess.sendMsg("\nDate: "+parseDate(news.appnews.getNewsitems().get(i).getDate())+"\n" +
                    news.appnews.getNewsitems().get(i).getTitle() + "\n\n" + cutter(cutter((cutter(news.appnews.getNewsitems().get(i).getContents(),'&',';')),'<', '>'),'[',']'), chatid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String parseDate(long total_sec) {
        long millis = total_sec * 1000;
        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE,MMMM d,yyyy h:mm,a", Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }
}
