package com.chatbot.rest;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

class RegistrationTest {

    final String REGISTER_URL = "http://localhost:5000/register";

    // testy do #7
    @Test
    void shouldReturn200StatusCodeWhenGettingRegisterPageTest() {

        given()
                .when()
                .get(REGISTER_URL)
                .then().statusCode(200);
    }

    @Test
    void shouldReturn201StatusCodeWhenUserSuccessfullyRegisteredTest() {
        Faker faker = new Faker();
        String randomEmail = faker.internet().emailAddress();
        String randomPassword = faker.internet().password();

        Map<String, Object> postBody = new HashMap<>();
        postBody.put("email", randomEmail);
        postBody.put("password", randomPassword);

        given().contentType(ContentType.JSON).
                and().body(postBody).
                when().post(REGISTER_URL).
                then().statusCode(201).
                and().body("message", is("User registered successfully"));
    }

    @Test
    void shouldReturn409StatusCodeWhenUserAlreadyRegisteredTest() {

        Map<String, Object> postBody = new HashMap<>();
        postBody.put("email", "bozena");
        postBody.put("password", "hoho3");

        given().contentType(ContentType.JSON).
                and().body(postBody).
                when().post(REGISTER_URL).
                then().statusCode(409)
                .and().body("error", is("Email already registered"));
    }
}