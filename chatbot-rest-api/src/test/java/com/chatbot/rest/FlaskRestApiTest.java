package com.chatbot.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
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

    @Test
    void testGetResponse() {
        RestAssured.baseURI = "http://localhost:5000";

        Response response = given()
                .when()
                .get("/").then().extract().response();

        String responseBody = response.prettyPrint();
        String expected = "<html>\n" +
                "  <body>\n" +
                "\n" +
                "    Hello, World!    <br clear=\"none\"/>\n" +
                "\n" +
                "    Debug=False    <br clear=\"none\"/>\n" +
                "\n" +
                "    DB=sqlite:///D:\\ProjectsPython\\cf_0625\\config\\chatbot.db\n" +
                "      </body>\n" +
                "</html>";

        Assertions.assertEquals(expected, responseBody);

    }
}
