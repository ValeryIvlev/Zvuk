package com.zvuk.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.zvuk.pages.component.AuthorizationComponent;
import com.zvuk.pages.component.NotificationComponent;
import com.zvuk.pages.component.SearchComponent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement searchDesktop = $("[class*='SearchDesktop_wrapper']");
    private final SelenideElement buttonNotifications = $("[class*='styles_notification']");
    private final SelenideElement colorTopicDark = $("#colt-theme-provider.colt-theme--dark");
    private final SelenideElement colorTopicLight = $("#colt-theme-provider.colt-theme--light");
    private final SelenideElement colorTopicCheckbox = $("label");
    private final SelenideElement authorizationButton = $(byText("Войти"));
    @Step("Открываем главную страницу")
    public MainPage openMainPage(){
        open("");
        return this;
    }
    @Step("Клик в область поиска")
    public SearchComponent clickOnSearch(){
        searchDesktop.click();
        return Selenide.page(SearchComponent.class);
    }
    @Step("Клик в область уведомлений")
    public NotificationComponent clickOnNotifications(){
        buttonNotifications.click();
        return Selenide.page(NotificationComponent.class);
    }
    @Step("Клик в область входа в аккаунт")
    public AuthorizationComponent clickOnAuthorizationButton(){
        authorizationButton.click();
        return Selenide.page(AuthorizationComponent.class);
    }
    @Step("Проверка темы темной страницы")
    public MainPage checkColorWindowIsDark(){
        colorTopicDark.shouldBe(visible);
        colorTopicLight.shouldBe(hidden);
        return this;
    }
    @Step("Проверка темы светлой страницы")
    public MainPage checkColorWindowIsLight(){
        colorTopicDark.shouldBe(hidden);
        colorTopicLight.shouldBe(visible);
        return this;
    }
    @Step("Проверка клика по чекбоксу переключения темы")
    public MainPage clickOnColorCheckbox(){
        colorTopicCheckbox.click();
        return this;
    }
}
