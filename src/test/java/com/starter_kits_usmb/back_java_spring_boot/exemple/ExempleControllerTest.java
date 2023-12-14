package com.starter_kits_usmb.back_java_spring_boot.exemple;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starter_kits_usmb.back_java_spring_boot.exemple.dto.ExempleCreateDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.security.test.context.support.WithMockUser;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-dev.properties")
class ExempleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllExemplesUnauthenticated() throws Exception {
        // Given

        // When
        ResultActions resultActions = mockMvc.perform(get("/api/exemples"));

        // Then
        resultActions
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser
    void getAllExemplesAuthenticated() throws Exception {
        // Given

        // When
        ResultActions resultActions = mockMvc.perform(get("/api/exemples"));

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void createExempleUnauthenticated() throws Exception {
        // Given
        ExempleCreateDTO exempleDTO = new ExempleCreateDTO();
        exempleDTO.setTitle("Example Title");
        exempleDTO.setDescription("Example Description");
        exempleDTO.setPublished(true);

        // When
        ResultActions resultActions = mockMvc.perform(post("/api/exemples")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(exempleDTO)));

        // Then
        resultActions
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser
    void createExempleNotAdmin() throws Exception {
        // Given
        ExempleCreateDTO exempleDTO = new ExempleCreateDTO();
        exempleDTO.setTitle("Example Title");
        exempleDTO.setDescription("Example Description");
        exempleDTO.setPublished(true);

        // When
        ResultActions resultActions = mockMvc.perform(post("/api/exemples")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(exempleDTO)));

        // Then
        resultActions
                .andExpect(status().isForbidden())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createExempleWithGoodData() throws Exception {
        // Given
        ExempleCreateDTO exempleDTO = new ExempleCreateDTO();
        exempleDTO.setTitle("Example Title");
        exempleDTO.setDescription("Example Description");
        exempleDTO.setPublished(true);

        // When
        ResultActions resultActions = mockMvc.perform(post("/api/exemples")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(exempleDTO)));

        // Then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(exempleDTO.getTitle()))
                .andExpect(jsonPath("$.description").value(exempleDTO.getDescription()))
                .andExpect(jsonPath("$.published").value(exempleDTO.isPublished()));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void createExempleWithBadData() throws Exception {
        // Given
        ExempleCreateDTO exempleDTO = new ExempleCreateDTO();
        exempleDTO.setTitle("");
        exempleDTO.setDescription("Example Description");
        exempleDTO.setPublished(true);

        // When
        ResultActions resultActions = mockMvc.perform(post("/api/exemples")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(exempleDTO)));

        // Then
        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}