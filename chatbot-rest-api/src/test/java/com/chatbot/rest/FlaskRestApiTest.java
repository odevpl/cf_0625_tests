package com.chatbot.rest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class FlaskRestApiTest {

    @Test
    void getApiTest() {

        RestAssured.baseURI = "http://localhost:5000";

        given()
                .when()
                .get("/")
                .then().statusCode(200);
    }
}
