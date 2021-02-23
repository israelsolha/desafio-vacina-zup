package com.israelsolha.vacinas.controllers;


import com.fasterxml.jackson.databind.json.JsonMapper;
import com.israelsolha.vacinas.prototypes.UserRequestPrototype;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.israelsolha.vacinas.prototypes.UserRequestPrototype.aUserRequestJson;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest @AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerTest {

    JsonMapper jsonMapper = new JsonMapper();
    @Autowired private MockMvc mockMvc;

    @Test public void testCreateUser() throws Exception {
        UserRequestPrototype userRequest = aUserRequestJson();
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                .contentType("application/json")
                .content(jsonMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated());
    }

    @Test public void testWrongDataGives400() throws Exception {
        UserRequestPrototype userRequest = aUserRequestJson();
        userRequest.setCpf("22222222222");
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                .contentType("application/json")
                .content(jsonMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test public void testDuplicateDataGives409() throws Exception {
        UserRequestPrototype userRequest = aUserRequestJson();
        UserRequestPrototype userRequest2 = aUserRequestJson();
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                .contentType("application/json")
                .content(jsonMapper.writeValueAsString(userRequest)));
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                .contentType("application/json")
                .content(jsonMapper.writeValueAsString(userRequest2)))
                .andExpect(status().isConflict());
    }

}
