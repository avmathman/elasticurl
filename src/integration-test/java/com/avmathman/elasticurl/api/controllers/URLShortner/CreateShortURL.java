package com.avmathman.elasticurl.api.controllers.URLShortner;

import com.avmathman.elasticurl.annotation.ElasticurlIntegrationTest;
import com.avmathman.elasticurl.api.ElasticURLApiLocations;
import com.avmathman.elasticurl.api.controllers.URLShortner.dto.ShortnerURLDto;
import com.avmathman.elasticurl.domain.URLShortner.URLShortnerModel;
import com.avmathman.elasticurl.domain.URLShortner.URLShortnerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ElasticurlIntegrationTest
public class CreateShortURL {

    private final String BASE_API_URL = ElasticURLApiLocations.URL_SHORTNER;

    @MockBean
    private URLShortnerService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createShortURL_passActualURL_returnShortURLModel() throws Exception {

        // Assign
        String actualURL = "https://wwww.test.com";

        ShortnerURLDto shortnerURLDto = new ShortnerURLDto();
        shortnerURLDto.setUrl(actualURL);

        URLShortnerModel model = new URLShortnerModel();
        model.setId("123");
        model.setShortURL("iz");
        model.setActualURL(actualURL);

        when(service.shortenURL(shortnerURLDto)).thenReturn(model);

        // Act
        String response = this.mockMvc
                .perform(
                        post(BASE_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(shortnerURLDto))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Assert
        assertThat(response).isNotNull();
//        assertThat(response).isEqualTo(ID.toString());
    }
}
