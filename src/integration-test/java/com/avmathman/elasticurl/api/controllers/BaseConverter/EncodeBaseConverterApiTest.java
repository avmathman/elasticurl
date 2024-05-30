package com.avmathman.elasticurl.api.controllers.BaseConverter;

import com.avmathman.elasticurl.annotation.ElasticurlIntegrationTest;
import com.avmathman.elasticurl.api.ElasticURLApiLocations;
import com.avmathman.elasticurl.domain.BaseConverter.Base64ConverterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ElasticurlIntegrationTest
public class EncodeBaseConverterApiTest {

    private final String BASE_API_URL = "/api" + ElasticURLApiLocations.CONVERTER + "/encode";

    @MockBean
    private Base64ConverterService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void encode_encodeById_returnStringValue() throws Exception {

        //Assign
        long ID = 123L;
        String expected = "1z";

        when(service.encode(anyLong())).thenReturn(expected);

        //Act
        String response = this.mockMvc
                .perform(get(BASE_API_URL))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //Assert
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(expected);
    }
}
