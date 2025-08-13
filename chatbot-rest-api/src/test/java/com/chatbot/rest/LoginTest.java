package com.chatbot.rest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class LoginTest {

    // testy do #8
    @Test
    void loginGetEndpointTest() {

        RestAssured.baseURI = "http://localhost:5000";

        given()
                .when()
                .get("/api/auth/login")
                //the correct URL should be "/login"
                .then().statusCode(200);
    }
}
