package com.company;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TopDeckOutput {
    private List<String> decks = new ArrayList();{
        decks.add("Black&Red \"HeroKiller\": ADCJewAJLkCAxiDU7G8AoKiAagBgbYBCqQBkAaaLQGLDw__");//Черно-красный «HeroKiller»
        decks.add("Blue&Green \"Combo\": ADCJaQAL7kCgQtNCX1dvd0BawGTh1xCgQEiAaICiISPU3oBjQ__");//Сине-зеленый «Combo»
        decks.add("Blue&Red \"Control\": ADCJYgAJLkCCgFMlrO8AgFZUp6BowFhAoFILQGKVWEBQQ__");//Сине-красный «Control»
        decks.add("Red&Green \"Buff Boys\": ADCJasAJLkCCUMMmXC7AqMBAVGUl02BBJ9hAhSsAa8BSA__");//Красно-зеленый «Buff Boys»
        decks.add("MonoRed: ADCJa0HJLkCQQmnAQEuuwKlAYEmAgMBCwMXCQEKKgGBikqSlrsBTW9ub1JlZA__");
        decks.add("MonoGreen: ADCJdMJPrgCklgLAba7AoapAYaNjRgBDgQMAgUJAbECBKECTW9ub0dyZWVu");
        decks.add("Blue&Green by Hyped: ADCJcU2L7kCQYsNCbhdgXfdAUqrAZqfpQNIhI9oAXIB0JrQvtC70L7QtNCwIEh5cGVkINCyINC-0LvQtdC5LdC+0YTRhCBXZVBsYXkhIFN0cmVuZ3Ro");
    }

    CardBot cardBot = new CardBot();

    void randomDeck(String chatid) {
        Random rnd = new Random(System.currentTimeMillis());
        int r = rnd.nextInt(decks.size());
        cardBot.sendMsg(decks.get(r), chatid);
    }

    void outputAllDecks(String chatid) {
        for (int i=0;i<decks.size();i++) {
            cardBot.sendMsg(decks.get(i), chatid);
        }
    }
}
