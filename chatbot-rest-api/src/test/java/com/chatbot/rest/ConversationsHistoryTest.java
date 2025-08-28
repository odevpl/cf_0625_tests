package com.chatbot.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.bytebuddy.agent.VirtualMachine;
import org.apache.el.parser.Token;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body(loginBody)
                        .when()
                        .log().all()
                        .post("/login")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract().response();

        return loginResponse.jsonPath().getString("token");
    }

    @Test
    void chatGetEndpointTest() {
        RestAssured.baseURI = "http://localhost:5000";

        given()
                .when()
                .get("/chat")
                .then().statusCode(200);
    }

    @Test
    void shouldReturn200StatusCodeWhenUserIsLoggedInTest() {
        RestAssured.baseURI = "http://localhost:5000";

        Map<String, Object> postBody = new HashMap<>();
        postBody.put("email", "testAga@test.com");
        postBody.put("password", "gaga5");

        given().contentType(ContentType.JSON).
                and().body(postBody).
                when().post("/login")
                .then().statusCode(200).
                and().body("message", is("Login successful"))
                .and().body("user_id", is(3));
    }

    @Test
    void shouldReturn200StatusCodeWhenPostMessageToChat() throws JsonProcessingException {
        RestAssured.baseURI = "http://localhost:5000";

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
    void shouldReturn200StatusCodeWhenGetConversationsEndpointTest() {
        RestAssured.baseURI = "http://localhost:5000";
        Response response =
                given()
                        .header("Authorization", "Bearer " + TOKEN)
                        .log().all()
                        .when()
                        .get("/conversations")
                        .then()
                        .statusCode(200)
                        .extract().response();

        String answer = response.jsonPath().getString("[0].odpowiedz");
        assertEquals("Przepraszam, jeszcze się uczę. Spróbuj inaczej.", answer);
    }
}
