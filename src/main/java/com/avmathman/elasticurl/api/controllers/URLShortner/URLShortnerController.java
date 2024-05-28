package com.avmathman.elasticurl.api.controllers.URLShortner;

import com.avmathman.elasticurl.api.ElasticURLApiLocations;
import com.avmathman.elasticurl.domain.URLShortner.URLShortnerModel;
import com.avmathman.elasticurl.domain.URLShortner.URLShortnerService;
import com.avmathman.elasticurl.api.controllers.URLShortner.dto.ShortnerURLDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * URL Shortner REST API controller with representing methods.
 */
@RestController
@RequestMapping(
        path = "${management.api.prefix:}" + ElasticURLApiLocations.URL_SHORTNER,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class URLShortnerController {

    private final URLShortnerService urlShortnerService;

    /**
     * Initializes a new {@link URLShortnerController} instance.
     *
     * @param urlShortnerService - {@link URLShortnerService} instance.
     */
    public URLShortnerController(URLShortnerService urlShortnerService) {
        this.urlShortnerService = urlShortnerService;
    }

    /**
     * REST API method to create a new short url.
     *
     * @param dto - The short URL to create.
     * @return The created user instance.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createShortURL(@RequestBody ShortnerURLDto dto) {
        URLShortnerModel shortURL = this.urlShortnerService.shortenURL(dto);

        return new ResponseEntity<>(shortURL.getShortURL(), HttpStatus.CREATED);
    }

    /**
     * REST API method to retrieve actual URL based on encoded ID.
     *
     * @return encoded ID.
     */
    @GetMapping("/{encodedId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<URLShortnerModel> getShortURL(@PathVariable String encodedId) {
        URLShortnerModel model = this.urlShortnerService.getShortURL(encodedId);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }
}
