package com.chatbot.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConversationManager {

    private Connection conn;
    private static DBConversationManager dbConversationManagerInstance;

    private DBConversationManager() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("email", "testAga@test.com");
        connectionProps.put("password", "gaga5");
        conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/Dell/PycharmProjects/cf_0625/config/chatbot.db", connectionProps);
    }

    public static DBConversationManager getInstance() throws SQLException {
        if (dbConversationManagerInstance == null) {
            dbConversationManagerInstance = new DBConversationManager();
        }
        return dbConversationManagerInstance;
    }

    public Connection getConnection() {
        return conn;
    }
}
