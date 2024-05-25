package com.avmathman.elasticurl.api.controllers.SnowflakeIDGenerator;

import com.avmathman.elasticurl.api.ElasticURLApiLocations;
import com.avmathman.elasticurl.common.enums.KeyEnum;
import com.avmathman.elasticurl.domain.SnowflakeIDGenerator.SnowflakeIDGeneratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Snowflake ID Generator REST API controller with representing methods.
 */
@RestController
@RequestMapping(
        path = "${management.api.prefix:}" + ElasticURLApiLocations.GENERATOR,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class SnowflakeIDGeneratorController {

    private final SnowflakeIDGeneratorService snowflakeIDGeneratorService;

    /**
     * Initializes a new {@link SnowflakeIDGeneratorController} instance.
     *
     * @param snowflakeIDGeneratorService - {@link SnowflakeIDGeneratorService} instance.
     */
    public SnowflakeIDGeneratorController(SnowflakeIDGeneratorService snowflakeIDGeneratorService) {
        this.snowflakeIDGeneratorService = snowflakeIDGeneratorService;
    }

    /**
     * REST API method to generate ID.
     *
     * @return generated ID.
     */
    @GetMapping("/generate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> generate() {
        Long generatedId = this.snowflakeIDGeneratorService.generate();

        return new ResponseEntity<>(generatedId, HttpStatus.OK);
    }

    /**
     * REST API method to parse generated ID.
     *
     * @param id - The ID to be parsed.
     * @return the user instance.
     */
    @GetMapping("/parse/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<KeyEnum, Long>> parse(@PathVariable Long id) {
        Map<KeyEnum, Long> parsed = this.snowflakeIDGeneratorService.parse(id);

        return new ResponseEntity<>(parsed, HttpStatus.OK);
    }
}
