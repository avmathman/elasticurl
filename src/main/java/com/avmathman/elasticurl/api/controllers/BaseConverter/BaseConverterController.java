package com.avmathman.elasticurl.api.controllers.BaseConverter;

import com.avmathman.elasticurl.api.ElasticURLApiLocations;
import com.avmathman.elasticurl.domain.BaseConverter.Base64ConverterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Base Converter REST API controller with representing methods.
 */
@RestController
@RequestMapping(
        path = "${management.api.prefix:}" + ElasticURLApiLocations.CONVERTER,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class BaseConverterController {

    private final Base64ConverterService baseConverter;

    /**
     * Initializes a new {@link BaseConverterController} instance.
     *
     * @param baseConverter - {@link Base64ConverterService} instance.
     */
    public BaseConverterController(Base64ConverterService baseConverter) {
        this.baseConverter = baseConverter;
    }

    /**
     * REST API method to encode given ID.
     *
     * @return decode base 62 ID.
     */
    @GetMapping("/encode/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> encode(@PathVariable Long id) {
        String encoded = this.baseConverter.encode(id);

        return new ResponseEntity<>(encoded, HttpStatus.OK);
    }

    /**
     * REST API method to decode given base 62 ID.
     *
     * @return decoded ID.
     */
    @GetMapping("/decode/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> decode(@PathVariable String id) {
        Long decoded = this.baseConverter.decode(id);

        return new ResponseEntity<>(decoded, HttpStatus.OK);
    }
}
