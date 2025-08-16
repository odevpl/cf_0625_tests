package com.chatbot.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

class LoginTest {
    final String LOGIN_URL = "http://localhost:5000/login";

    @Test
    void shouldReturn200StatusCodeWhenGettingLoginPageTest() {
        when().get(LOGIN_URL)
                .then().statusCode(200);
    }

    @Test
    void shouldReturn200StatusCodeWhenUserIsLoggedInTest() {
        Map<String, Object> postBody = new HashMap<>();
        postBody.put("email", "bozena");
        postBody.put("password", "hoho3");

        given().contentType(ContentType.JSON).
                and().body(postBody).
                when().post(LOGIN_URL).
                then().statusCode(200).
                and().body("message", is("Login successful"))
                .and().body("token", startsWith("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"))
                .and().body("user_id", is(1));
    }

    @Test
    void shouldReturn400StatusCodeWhenOnlyEmailProvidedTest() {
        Map<String, Object> postBody = new HashMap<>();
        postBody.put("email", "bozena");

        given().contentType(ContentType.JSON).
                and().body(postBody).
                when().post(LOGIN_URL).
                then().statusCode(400).
                and().body("error", is("Email and password are required"));
    }

    @Test
    void shouldReturn400StatusCodeWhenOnlyPasswordProvidedTest() {
        Map<String, Object> postBody = new HashMap<>();
        postBody.put("password", "hoho3");

        given().contentType(ContentType.JSON).
                and().body(postBody).
                when().post(LOGIN_URL).
                then().statusCode(400).
                and().body("error", is("Email and password are required"));
    }

    @Test
    void shouldReturn401StatusCodeWhenWrongEmailTest() {
        Map<String, Object> postBody = new HashMap<>();
        postBody.put("email", "bozenaha");
        postBody.put("password", "hoho3");

        given().contentType(ContentType.JSON).
                and().body(postBody).
                when().post(LOGIN_URL).
                then().statusCode(401).
                and().body("error", is("Invalid email"));
    }

    @Test
    void shouldReturn401StatusCodeWhenWrongPasswordTest() {
        Map<String, Object> postBody = new HashMap<>();
        postBody.put("email", "bozena");
        postBody.put("password", "hoho34");

        given().contentType(ContentType.JSON).
                and().body(postBody).
                when().post(LOGIN_URL).
                then().statusCode(401).
                and().body("error", is("Invalid password"));
    }
}