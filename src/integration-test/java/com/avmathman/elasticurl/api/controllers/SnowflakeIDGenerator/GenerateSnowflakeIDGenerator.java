package com.avmathman.elasticurl.api.controllers.SnowflakeIDGenerator;

import com.avmathman.elasticurl.annotation.ElasticurlIntegrationTest;
import com.avmathman.elasticurl.api.ElasticURLApiLocations;
import com.avmathman.elasticurl.domain.SnowflakeIDGenerator.SnowflakeIDGeneratorService;
import com.avmathman.elasticurl.domain.SnowflakeIDGenerator.exception.SnowflakeIdGeneratorException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ElasticurlIntegrationTest
public class GenerateSnowflakeIDGenerator {

    private final String BASE_API_URL = ElasticURLApiLocations.GENERATOR + "/generate";

    @MockBean
    private SnowflakeIDGeneratorService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void generate_generateValue_returnStringValue() throws Exception {

        //Assign
        Long ID = 123L;

        when(service.generate()).thenReturn(ID);

        //Act
        String response = this.mockMvc
                .perform(get(BASE_API_URL))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Assert
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(ID.toString());
    }

    @Test
    public void generate_generateValue_throwsIllegalStateException() throws Exception {

        //Assign
        Long ID = 123L;
        String expected = "1z";

        when(service.generate()).thenThrow(SnowflakeIdGeneratorException.class);

        //Act
        this.mockMvc
                .perform(get(BASE_API_URL))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertInstanceOf(SnowflakeIdGeneratorException.class, result.getResolvedException()));
    }
}
