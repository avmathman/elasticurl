package com.avmathman.elasticurl.domain.URLShortner;

import com.avmathman.elasticurl.domain.URLShortner.dto.ShortnerURLDto;
import org.springframework.stereotype.Service;

@Service
public class URLShortnerService implements IURLShortner {

    @Override
    public String shortenURL(ShortnerURLDto dto) {
        return "";
    }

    @Override
    public String getShortURL(String encodedId) {
        return "";
    }

}
