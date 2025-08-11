package com.chatbot.rest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class ChatEndpointTest {

    //testy to task #10
    @Test
    void chatGetEndpointTest() {
        RestAssured.baseURI = "http://localhost:5000";

        given()
                .when()
                .get("/chat")
                .then().statusCode(200);

    }

    @Test
    void questionMissingTest() {

    }
}
