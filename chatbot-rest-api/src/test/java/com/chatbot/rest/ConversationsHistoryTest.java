package com.chatbot.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConversationsHistoryTest {

    private static String TOKEN;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost:5000";
        TOKEN = givenLoginToken("testAga@test.com", "gaga5");
    }

    private static String givenLoginToken(String email, String password) {
        Map<String, String> loginBody = new HashMap<>();
        loginBody.put("email", email);
        loginBody.put("password", password);

        Response loginResponse =
                given()
                        .contentType(ContentType.JSON)
                        .body(loginBody)
                        .when()
                        .post("/login")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().response();

        return loginResponse.jsonPath().getString("token");
    }

    @Test
    void chatGetEndpointTest() {

        given()
                .when()
                .get("/chat")
                .then().statusCode(200);
    }

    @Test
    void shouldReturn200StatusCodeWhenUserIsLoggedInTest() {

        Map<String, Object> postBody = new HashMap<>();
        postBody.put("email", "testAga@test.com");
        postBody.put("password", "gaga5");

        given().contentType(ContentType.JSON)
                .and().body(postBody)
                .when().post("/login")
                .then().statusCode(200)
                .and().body("message", is("Login successful"))
                .and().body("user_id", is(3));
    }

    @Test
    void shouldReturn200StatusCodeWhenPostMessageToChat() throws JsonProcessingException {

        Map<String, Object> postBody = new HashMap<>();
        postBody.put("user", "testAga@test.com");
        postBody.put("message", "hello bot");

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(postBody);

        given()
                .log().all()
                .header("Authorization", "Bearer " + TOKEN)
                .header("Content-Type", "application/json")
                .and().body(jsonBody)
                .when().post("/chat")
                .then()
                .log().all()
                .statusCode(200)
                .and()
                .body("response", is("Przepraszam, jeszcze się uczę. Spróbuj inaczej."))
                .log().all();
    }

    @Test
    void shouldValidateConversationsFieldsTest() {
        Response response =
                given()
                        .header("Authorization", "Bearer " + TOKEN)
                        .when()
                        .get("/conversations")
                        .then()
                        .statusCode(200)
                        .body("[0].pytanie", equalTo("hello bot"))
                        .body("[0].odpowiedz", notNullValue())
                        .body("[0].data", notNullValue())
                        .extract().response();

        //validate message/answer/timestamp
        List<String> dates = response.jsonPath().getList("data");
        List<String> answers = response.jsonPath().getList("odpowiedz");
        List<String> questions = response.jsonPath().getList("pytanie");

        //checking if the number of answers is the same as the number of questions
        assertEquals(dates.size(), answers.size());
        assertEquals(answers.size(), questions.size());

        //validating fields/for now every answer is the same
        for (int i = 0; i < dates.size(); i++) {
            assertNotNull(dates.get(i), "field 'date' is not null: " + i);
            assertEquals("Przepraszam, jeszcze się uczę. Spróbuj inaczej.", answers.get(i));
            assertEquals("hello bot", questions.get(i));
        }
    }
}
