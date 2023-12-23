package com.zvuk.pages.component;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AuthorizationComponent {
    private final ElementsCollection countriesList = $$("li");
    private final SelenideElement authorizationForm = $("[class*='AuthSelector_wrapper']");
    private final SelenideElement spinnerLoad = $("[class*='Spinner__root']");
    private final SelenideElement regionPhoneButton = $("[class*='Button__content']");
    @Step("Проверяем открытие компонента авторизации после перехода внутрь фрейма")
    public AuthorizationComponent checkOpenAuthorizationComponent(){
        switchTo().frame(0);
        authorizationForm.shouldBe(visible);
        spinnerLoad.shouldBe(visible);
        authorizationForm.shouldBe(visible);
        return this;
    }
    @Step("Кликаем по кнопке выбора региона")
    public AuthorizationComponent clickOnSelectRegionButton(){
        regionPhoneButton.click();
        return this;
    }
    @Step("Сверяем страны с актуальным списом")
    public AuthorizationComponent checkCountriesList(List<String> countries){
        for (int i = 0; i < countries.size(); i++) {
            countriesList.get(i).shouldHave(text(countries.get(i)));
        }
        return this;
    }
}