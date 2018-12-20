package com.company;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import java.io.IOException;
import java.util.List;

class CardSet {
    private int version;
    private SetInfo set_info;
    private List<CardList> card_list;

    public List<CardList> getCard_list() {
        return card_list;
    }

    public int getVersion() {
        return version;
    }

    public SetInfo getSet_info() {
        return set_info;
    }
}

class CardList {
    private Image large_image;
    private List<Object> card_list;
    private String card_type;
    private String rarity;
    private CardName card_name;
    private CardText card_text;

    public String getCard_type() {
        return card_type;
    }

    public String getRarity() {
        return rarity;
    }

    public Image getLarge_image() {
        return large_image;
    }

    public CardText getCard_text() {
        return card_text;
    }

    public CardName getCard_name() {
        return card_name;
    }

    public List<Object> getCard_list() {
        return card_list;
    }
}

class Image {
    private String russian;

    public String getRussian() {
        return russian;
    }
}

class CardText {
    private String english;
    private String russian;

    public String getRussian() {
        return russian;
    }

    public String getEnglish() {
        return english;
    }
}

class CardName {
    private String english;
    private String russian;

    public String getRussian() {
        return russian;
    }

    public String getEnglish() {
        return english;
    }
}

class SetInfo {
    private Name name;

    public Name getName() {
        return name;
    }
}

public class ArtifactCards {
    private static ArtifactCards sInstance;
    private OkHttpClient okHttpClient = new OkHttpClient();

    private CardSet card_set;


    public static ArtifactCards getInstance() {
        if (sInstance == null)
            sInstance = new ArtifactCards();
        return sInstance;
    }

    public void printCardInfo(String text, String chatid) {
        Request r = new Request.Builder()
                .url("https://steamcdn-a.akamaihd.net/apps/583950/resource/card_set_1." +
                        "D7F0218A9EB22B71000F1E53740E1A6DE1196BD5.json")
                .get()
                .build();
        Call allCountriesCall = okHttpClient.newCall(r);
        try {
            CardBot mess = new CardBot();
            Response response = allCountriesCall.execute();
            ResponseBody body = response.body();
            String bodystring = body.string();
            ArtifactCards games = new Gson().fromJson(bodystring, ArtifactCards.class);
            for (int i = 0; i < (games.card_set.getCard_list().size()); i++) {
                if (text.equalsIgnoreCase(games.card_set.getCard_list().get(i).getCard_name().getEnglish()) || text.equalsIgnoreCase(games.card_set.getCard_list().get(i).getCard_name().getRussian())) {

                    mess.sendMsg("Name: " + games.card_set.getCard_list().get(i).getCard_name().getEnglish() +
                            "\nAbility: " + cutter(games.card_set.getCard_list().get(i).getCard_text().getEnglish(), '<', '>') +
                            "\nRarity: " + games.card_set.getCard_list().get(i).getRarity() +
                            "\nType: " + games.card_set.getCard_list().get(i).getCard_type() +
                            "\nImage: " + games.card_set.getCard_list().get(i).getLarge_image().getRussian(), chatid);
                    mess.sendMsg("Write note for this card.(0-5)", chatid);
                   return;
                }
            }
            mess.sendMsg("I can't found this card", chatid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String cutter(String str, char firstReplacable, char secondReplacable) {
        String temp = "";
        boolean flag = true;
        if (str != null) {
            for (int i = 0; i < str.length() - 1; i++) {
                if (str.charAt(i) == firstReplacable) {
                    flag = false;
                }
                if (flag == true) {
                    temp += str.substring(i, i + 1);
                }
                if (str.charAt(i) == secondReplacable) {
                    flag = true;
                }
            }
            return temp;
        } else return "No information";
    }
}
