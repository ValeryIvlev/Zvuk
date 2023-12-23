package com.zvuk.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ArtistPage {
    private final SelenideElement artistInfo = $("[class*='styles_infoWrapper']");

    @Step("Проверка имени Артиста на его странице")
    public ArtistPage checkNameArtist(String nameArtist){
        artistInfo.shouldHave(text(nameArtist));
        return this;
    }
}
