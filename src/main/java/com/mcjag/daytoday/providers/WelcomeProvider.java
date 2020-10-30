package com.mcjag.daytoday.providers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WelcomeProvider {

    public String hello(String name) {
        return String.format("Hello %s!", name);
    }

    public String calendar() {
        return "Welcome to your Calendar!";
    }

    public String onThisDay() throws IOException {
        Document doc = Jsoup.connect("https://www.history.com/this-day-in-history").get();
        String keywords = doc.select("meta[itemprop=name]").get(1).attr("content");
        return String.format("On this day: %s", keywords);
    }
}
