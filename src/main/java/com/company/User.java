package com.company;

public class User {


    private static final String Help = "Hi!\nI can tell you the latest news from the world of \"Artifact\", suggest you a deck and show any card description. "+
            "Just write the name of any card or choose an option from the Telegram keyboard. Have fun!";
    private static final String AdminId = "436046265";


    public void checkMessage(String text, String chatid) {
        CardBot e = new CardBot();
        ArtifactCards card = new ArtifactCards();
        TopDeckOutput topDeckOutput = new TopDeckOutput();
        if (text.equalsIgnoreCase("/start") || text.equalsIgnoreCase("/help") || text.equalsIgnoreCase("start") || text.equalsIgnoreCase("help")) {
            e.sendMsg( Help, chatid);
        }else if(text.equalsIgnoreCase("Give me a random deck")){
            topDeckOutput.randomDeck(chatid);
        }else if(text.equalsIgnoreCase("Give me all the decks")){
            topDeckOutput.outputAllDecks(chatid);
        }else if(text.equalsIgnoreCase("The latest news")){
            ArtifactNews artifactNews = new ArtifactNews();
            artifactNews.printNews(chatid);
        }else if (text.equalsIgnoreCase("The latest 3 news")){
            ArtifactNews artifactNews = new ArtifactNews();
            artifactNews.printThreeNews(chatid);
        }else {
            card.printCardInfo(text, chatid);
        }
    }

}
