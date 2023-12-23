package com.zvuk;

import com.zvuk.api.ApiSearch;
import com.zvuk.pages.MainPage;
import com.zvuk.testdata.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class SelenideTests extends TestBase {
    MainPage mainPage = new MainPage();

    @Test
    @Tag("zvuk")
    @DisplayName("Проверка поисовой выдачи при поиске по Артисту")
    void checkSearchArtist() throws IOException {
        TestData testData = new TestData();
        ApiSearch apiHelpers = new ApiSearch(testData.artist);
        mainPage.openMainPage()
                .clickOnSearch()
                .enterText(testData.artist)
                .hoverSearchInput()
                .checkSearchElement(apiHelpers.getTitles());
    }

    @Test
    @Tag("zvuk")
    @DisplayName("Проверка количества поисовой выдачи при поиске по Артисту")
    void checkSearchCount() throws IOException {
        TestData testData = new TestData();
        ApiSearch apiHelpers = new ApiSearch(testData.artist);
        mainPage.openMainPage()
                .clickOnSearch()
                .enterText(testData.artist)
                .hoverSearchInput()
                .checkCountSearchResult(apiHelpers.getCountSearchResult());
    }

    @Test
    @Tag("zvuk")
    @DisplayName("Проверка отсутствия уведомлений")
    void notificationsAbsence() {
        mainPage.openMainPage()
                .clickOnNotifications()
                .checkEmptyResult();
    }

    @Test
    @Tag("zvuk")
    @DisplayName("Проверка переключения цвета темы")
    void checkColorSwitching() {
        mainPage.openMainPage()
                .checkColorWindowIsDark()
                .clickOnColorCheckbox()
                .checkColorWindowIsLight();
    }

    @Test
    @Tag("zvuk")
    @DisplayName("Проверка регионов для авторизации в сервисе")
    void checkRegionsAuthorization() {
        TestData testData = new TestData();
        mainPage.openMainPage()
                .clickOnAuthorizationButton()
                .checkOpenAuthorizationComponent()
                .clickOnSelectRegionButton()
                .checkCountriesList(testData.countries);
    }
}
