package com.zvuk.testdata;


import java.util.List;

import java.util.Random;

public class TestData {
    Random random = new Random();
    private final List<String> artists = List.of("Клава кока", "Zivert", "Король и шут");
    public String artist = artists.get(random.nextInt(0,3));
    public final List<String> countries = List.of("Российская Федерация", "Азербайджан", "Армения", "Беларусь"
            , "Грузия", "Казахстан", "Киргизия", "Молдавия", "Таджикистан", "Туркмения", "Узбекистан", "Украина");

}
