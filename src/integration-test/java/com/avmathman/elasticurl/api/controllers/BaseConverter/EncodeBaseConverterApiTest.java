package com.avmathman.elasticurl.api.controllers.BaseConverter;

import com.avmathman.elasticurl.annotation.ElasticurlIntegrationTest;
import com.avmathman.elasticurl.api.ElasticURLApiLocations;
import com.avmathman.elasticurl.domain.BaseConverter.Base64ConverterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ElasticurlIntegrationTest
public class EncodeBaseConverterApiTest {

    private final String BASE_API_URL = ElasticURLApiLocations.CONVERTER + "/encode";

    @MockBean
    private Base64ConverterService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void encode_encodeById_returnStringValue() throws Exception {

        //Assign
        long ID = 123L;
        String expected = "1z";

        when(service.encode(anyLong())).thenReturn(expected);

        //Act
        String response = this.mockMvc
                .perform(get(BASE_API_URL + "/{id}", ID))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Assert
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(expected);
    }

    @Test
    public void encode_encodeById_checksWithInvalidValue() throws Exception {

        //Assign
        long ID = 123L;
        String expected = "1z";
        String incorrect = "123abc";

        when(service.encode(anyLong())).thenReturn(expected);

        //Act
        String response = this.mockMvc
                .perform(get(BASE_API_URL + "/{id}", ID))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Assert
        assertThat(response).isNotNull();
        assertThat(response).isNotEqualTo(incorrect);
    }
}
