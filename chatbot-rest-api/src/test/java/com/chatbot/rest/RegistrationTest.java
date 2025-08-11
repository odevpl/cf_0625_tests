package com.chatbot.rest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class RegistrationTest {

    // testy do #7
    @Test
    void registerGetEndpointTest() {

        RestAssured.baseURI = "http://localhost:5000";

        given()
                .when()
                .get("/register")
                .then().statusCode(200);
    }
}
