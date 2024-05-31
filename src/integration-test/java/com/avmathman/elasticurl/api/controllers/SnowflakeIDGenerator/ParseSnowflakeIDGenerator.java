package com.avmathman.elasticurl.api.controllers.SnowflakeIDGenerator;

import com.avmathman.elasticurl.annotation.ElasticurlIntegrationTest;
import com.avmathman.elasticurl.api.ElasticURLApiLocations;
import com.avmathman.elasticurl.common.enums.KeyEnum;
import com.avmathman.elasticurl.domain.SnowflakeIDGenerator.SnowflakeIDGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ElasticurlIntegrationTest
public class ParseSnowflakeIDGenerator {

    private final String BASE_API_URL = ElasticURLApiLocations.GENERATOR + "/parse/{id}";

    @MockBean
    private SnowflakeIDGeneratorService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void parse_passIDValue_returnStringValue() throws Exception {

        //Assign
        Long ID = 1246019296027607040L;

        Map<KeyEnum, Long> expected = new HashMap<>();
        expected.put(KeyEnum.TIMESTAMP, 1717144550092L);
        expected.put(KeyEnum.DATACENTER, 1L);
        expected.put(KeyEnum.MACHINE, 0L);
        expected.put(KeyEnum.SEQUENCE, 0L);

        when(service.parse(ID)).thenReturn(expected);

        //Act
        String response = this.mockMvc
                .perform(get(BASE_API_URL, ID))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Map<String, Long> result = convertToMap(response);

        //Assert
        assertThat(response).isNotNull();
        assertThat(result.get("TIMESTAMP")).isEqualTo(1717144550092L);
        assertThat(result.get("DATACENTER")).isEqualTo(1L);
        assertThat(result.get("MACHINE")).isEqualTo(0L);
        assertThat(result.get("SEQUENCE")).isEqualTo(0L);
    }

    private Map<String, Long> convertToMap(String value) {
        String[] keyValuePairs = value.substring(1, value.length() - 1).split(",");

        return Arrays.stream(keyValuePairs).map(entry -> entry.split(":"))
                .collect(Collectors.toMap(
                        pair -> pair[0].substring(1, pair[0].length() - 1),
                        pair -> Long.parseLong(pair[1])
                ));
    }
}
