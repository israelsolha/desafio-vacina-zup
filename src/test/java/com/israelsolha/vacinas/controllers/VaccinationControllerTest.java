package com.israelsolha.vacinas.controllers;


import com.fasterxml.jackson.databind.json.JsonMapper;
import com.israelsolha.vacinas.prototypes.UserRequestPrototype;
import com.israelsolha.vacinas.prototypes.VaccinationRequestPrototype;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.israelsolha.vacinas.prototypes.UserRequestPrototype.aUserRequestJson;
import static com.israelsolha.vacinas.prototypes.VaccinationRequestPrototype.aVaccinationRequestJson;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest @AutoConfigureMockMvc public class VaccinationControllerTest {

    @Autowired private MockMvc mockMvc;

    JsonMapper jsonMapper = new JsonMapper();

    @Test public void testCreateVaccination() throws Exception {
        UserRequestPrototype userRequest = aUserRequestJson();
        VaccinationRequestPrototype vaccinationRequest =
                aVaccinationRequestJson();
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                .contentType("application/json")
                .content(jsonMapper.writeValueAsString(userRequest)));
        mockMvc.perform(MockMvcRequestBuilders.post("/vaccinations/create")
                .contentType("application/json")
                .content(jsonMapper.writeValueAsString(vaccinationRequest)))
                .andExpect(status().isCreated());
    }

    @Test public void testWrongDataGives400() throws Exception {
        VaccinationRequestPrototype vaccinationRequest =
                aVaccinationRequestJson();
        vaccinationRequest.setVaccineName(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/vaccinations/create")
                .contentType("application/json")
                .content(jsonMapper.writeValueAsString(vaccinationRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test public void testUnusedEmailGives412() throws Exception {
        VaccinationRequestPrototype vaccinationRequest =
                aVaccinationRequestJson();
        vaccinationRequest.setEmail("emailnaousado@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders.post("/vaccinations/create")
                .contentType("application/json")
                .content(jsonMapper.writeValueAsString(vaccinationRequest)))
                .andExpect(status().isPreconditionFailed());
    }

}
