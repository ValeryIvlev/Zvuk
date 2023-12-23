package com.zvuk.pages.component;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.zvuk.pages.ArtistPage;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchComponent {
    private final ElementsCollection listResultSearch = $$("[class*='SuggestionsDesktop_suggest']")
            .filter(attribute("tabindex"));
    private final SelenideElement desktopInput = $("[class*='DesktopInput']");
    private final SelenideElement windowSearchResult = $("[class*='SuggestionsDesktop_listSuggestions']");
    private final ElementsCollection title = $$("[class*='SuggestionsDesktop_suggestTitle']");

    @Step("Ввод текста в строку поиска")
    public SearchComponent enterText(String text){
        desktopInput.sendKeys(text);
        return this;
    }

    @Step("Наводим курсор на строку поиска")
    public SearchComponent hoverSearchInput(){
        desktopInput.hover();
        return this;
    }

    @Step("Проверка количества результатов в выдаче")
    public SearchComponent checkCountSearchResult(int count){
        windowSearchResult.shouldBe(visible);
        assertEquals(count, listResultSearch.size());
        return this;
    }
    @Step("Проверка упоминания артиста в результатах поиска")
    public SearchComponent checkSearchElement(List<String> titles) {
        for (int i = 0; i < titles.size(); i++) {
            title.get(i).shouldHave(text(titles.get(i)));
        }
        return this;
    }
    @Step("Кликаем по Артисту в поиске")
    public ArtistPage clickOnArtist(){
        title.get(0).click();
        return Selenide.page(ArtistPage.class);
    }
}