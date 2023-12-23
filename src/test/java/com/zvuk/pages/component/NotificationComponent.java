package com.zvuk.pages.component;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class NotificationComponent {
    private final SelenideElement notificationModalWindow = $("[class*='styles_notCentered']");

    @Step("Проверка отсутствия уведомлений")
    public NotificationComponent checkEmptyResult(){
        notificationModalWindow.shouldHave(Condition.text("Пока без новостей"));
        return this;
    }
}