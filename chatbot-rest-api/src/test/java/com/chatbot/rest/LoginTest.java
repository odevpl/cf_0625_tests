package com.chatbot.rest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LoginTest {

    // testy do #8
    @Test
    void loginGetEndpointTest() {

        RestAssured.baseURI = "http://localhost:5000";

        given()
                .when()
                .get("/login")
                .then().statusCode(200);
    }
}
