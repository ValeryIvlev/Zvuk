package com.zvuk;

import com.codeborne.selenide.Configuration;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.zvuk.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @BeforeAll
    static void beforeAll() {

        Configuration.baseUrl = "";
        Configuration.browserSize = System.getProperty("size", "1920x1080");
        //Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 20000;
        //Configuration.browserVersion = System.getProperty("version", "98");
        Configuration.headless = false;
        Configuration.webdriverLogsEnabled = true;
        Configuration.browser = System.getProperty("browser", "firefox");

        //Configuration.remote
        //        = System.getProperty("selenoid", "https://user1:1234@selenoid.autotests.cloud/wd/hub");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void setUp() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        //Attach.browserConsoleLogs();
        Attach.addVideo();

        clearBrowserCookies();
        clearBrowserLocalStorage();
        closeWebDriver();
    }
}