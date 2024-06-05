package com.avmathman.elasticurl.domain.URLShortner;

import com.avmathman.elasticurl.ElasticurlApplication;
import com.avmathman.elasticurl.api.controllers.URLShortner.dto.ShortnerURLDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ElasticurlApplication.class})
@ActiveProfiles("test")
public class URLShortnerServiceTest {

    @Autowired
    private URLShortnerService urlShortnerService;

    @Test
    public void shortenURL() {
        String actualURL = "https://wwww.test.com";

        ShortnerURLDto shortnerURLDto = new ShortnerURLDto();
        shortnerURLDto.setUrl(actualURL);

        URLShortnerModel shortened = urlShortnerService.shortenURL(shortnerURLDto);

        assertNotNull(shortened);
        assertFalse(shortened.getId().isEmpty());
        assertFalse(shortened.getShortURL().isEmpty());
        assertEquals(actualURL, shortened.getActualURL());

    }

    @Test
    public void getShortURL() {
        String actualURL = "https://wwww.test.com";

        ShortnerURLDto shortnerURLDto = new ShortnerURLDto();
        shortnerURLDto.setUrl(actualURL);

        URLShortnerModel shortened = urlShortnerService.shortenURL(shortnerURLDto);
        URLShortnerModel current = urlShortnerService.getShortURL(shortened.getShortURL());

        assertNotNull(current);
        assertFalse(current.getId().isEmpty());
        assertFalse(current.getShortURL().isEmpty());
        assertEquals(actualURL, current.getActualURL());
    }
}
