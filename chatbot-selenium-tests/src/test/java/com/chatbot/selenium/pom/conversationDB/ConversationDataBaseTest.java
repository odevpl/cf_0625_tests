package com.chatbot.selenium.pom.conversationDB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ConversationDataBaseTest {

    private static DBConversationManager dbConversationManager;

    @BeforeAll
    static void setup() throws SQLException {
        dbConversationManager = DBConversationManager.getInstance();
    }

    @Test
    void connectionTest() {
        Assertions.assertNotNull(dbConversationManager.getConnection());
    }
}
