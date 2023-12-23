package com.zvuk.api;

import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiSearch {
    private final JsonPath JsonBody;
    public ApiSearch(String artistName) throws IOException {
        this.JsonBody = getSearchResult(artistName);
    }

    @Step("Получение токена")
    private static String getToken(){
        return given()
                .when()
                .get("https://zvuk.com/api/tiny/profile")
                .then()
                .extract().response().jsonPath().getString("result.token");
    }
    @Step("Получение боди результатов поиска")
    public static JsonPath getSearchResult(String artist) throws IOException {
        String persistedQuery = Files.readString(Path.of("src/test/resources/persistedQuery.json"));
        String body = "{\n" +
                "  \"query\": "+persistedQuery+","+
                "  \"variables\": {\n" +
                "   \"query\": \""+artist+"\",\n" +
                "    \"limit\": 5,\n" +
                "    \"searchSessionId\": \"6d93c8aa-8600-4bba-865c-146aaa6bec6a\"\n" +
                "  },\n" +
                "  \"operationName\": \"getQuickSearch\"\n" +
                "}";
        JsonPath result = given()
                .when()
                .header("content-type", "application/json")
                .header("x-auth-token", getToken())
                .body(body)
                .post("https://zvuk.com/api/v1/graphql")
                .then()
                .extract().response().jsonPath();
        return result;
    }
    @Step("Получаем все титлы из боди")
    public List<String> getTitles(){
        return JsonBody.getList("data.quickSearch.content.title");
    }
    @Step("Получаем количество результатов в поисковой выдаче")
    public int getCountSearchResult() {
        return JsonBody.getList("data.quickSearch.content.id").size();
    }
}