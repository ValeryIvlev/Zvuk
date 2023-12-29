package com.zvuk;


import com.github.javafaker.Faker;

import java.util.List;
import java.util.Locale;


public class TestData {
    private final Faker faker = new Faker(new Locale("ru"));
    public String artist = faker.options().option("Клава кока", "Zivert", "Король и шут");
    public final List<String> countries = List.of("Российская Федерация", "Азербайджан", "Армения", "Беларусь"
            , "Грузия", "Казахстан", "Киргизия", "Молдавия", "Таджикистан", "Туркмения", "Узбекистан", "Украина");

}
