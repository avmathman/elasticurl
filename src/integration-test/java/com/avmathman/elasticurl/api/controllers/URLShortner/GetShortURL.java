package com.avmathman.elasticurl.api.controllers.URLShortner;

import com.avmathman.elasticurl.annotation.ElasticurlIntegrationTest;
import com.avmathman.elasticurl.api.ElasticURLApiLocations;
import com.avmathman.elasticurl.domain.URLShortner.URLShortnerModel;
import com.avmathman.elasticurl.domain.URLShortner.URLShortnerService;
import com.avmathman.elasticurl.domain.URLShortner.exception.URLNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ElasticurlIntegrationTest
public class GetShortURL {

    private final String BASE_API_URL = ElasticURLApiLocations.URL_SHORTNER;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private URLShortnerService service;

    @Test
    public void getShortURL_passEncodedID_returnURLShortnerModel() throws Exception {

        // Assign
        String actualURL = "https://wwww.test.com";
        String ID = "123";
        String encodedId = "iz";

        URLShortnerModel model = new URLShortnerModel();
        model.setId(ID);
        model.setShortURL(encodedId);
        model.setActualURL(actualURL);

        when(service.getShortURL(anyString())).thenReturn(model);

        // Act
        String response = this.mockMvc
                .perform(get(BASE_API_URL + "/{encodedId}", encodedId))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Assert
        assertThat(response).isNotNull();

        URLShortnerModel result = objectMapper.readValue(response, URLShortnerModel.class);
        assertThat(result.getId()).isEqualTo(model.getId());
        assertThat(result.getShortURL()).isEqualTo(model.getShortURL());
        assertThat(result.getActualURL()).isEqualTo(model.getActualURL());
    }

    @Test
    public void getShortURL_passEncodedID_throwURLNotFoundException() throws Exception {

        // Assign
        String encodedId = "iz";

        when(service.getShortURL(anyString())).thenThrow(new URLNotFoundException("No data found, encoded ID: " + encodedId));

        // Act
        String response = this.mockMvc
                .perform(get(BASE_API_URL + "/{encodedId}", encodedId))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResolvedException()
                .getMessage();

        // Assert
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo("No data found, encoded ID: " + encodedId);
    }
}
