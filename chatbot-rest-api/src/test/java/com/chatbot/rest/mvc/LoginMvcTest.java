package com.chatbot.rest.mvc;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(ChatbotController.class)
public class LoginMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getEndpointTest() throws Exception {
        //when & then
        mockMvc.perform(MockMvcRequestBuilders.get("http://127.0.0.1:5000/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().is(200));
    }
}

