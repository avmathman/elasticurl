package com.avmathman.elasticurl.api.controllers.BaseConverter;

import com.avmathman.elasticurl.annotation.ElasticurlIntegrationTest;
import com.avmathman.elasticurl.api.ElasticURLApiLocations;
import com.avmathman.elasticurl.domain.BaseConverter.Base64ConverterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ElasticurlIntegrationTest
public class DecodeBaseConverterApiTest {

    private final String BASE_API_URL = ElasticURLApiLocations.CONVERTER + "/decode";

    @MockBean
    private Base64ConverterService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void decode_decodeByValue_returnsEncodedValue() throws Exception {

        //Assign
        String value = "1z";
        Long expected = 123L;

        when(service.decode(anyString())).thenReturn(expected);

        //Act
        String response = this.mockMvc
                .perform(get(BASE_API_URL + "/{id}", value))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Assert
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(expected.toString());
    }

    @Test
    public void decode_decodeByValue_returnsIncorrectEncodedValue() throws Exception {

        //Assign
        String value = "1z";
        Long expected = 123L;
        String incorrect = "123";

        when(service.decode(anyString())).thenReturn(expected);

        //Act
        String response = this.mockMvc
                .perform(get(BASE_API_URL + "/{id}", value))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Assert
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(incorrect);
    }
}
