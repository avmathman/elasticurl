package com.avmathman.elasticurl.domain.URLShortner;

import com.avmathman.elasticurl.data.URLShortner.URLShortnerEntity;
import com.avmathman.elasticurl.data.URLShortner.URLShortnerRepository;
import com.avmathman.elasticurl.domain.BaseConverter.Base64ConverterService;
import com.avmathman.elasticurl.domain.SnowflakeIDGenerator.SnowflakeIDGeneratorService;
import com.avmathman.elasticurl.api.controllers.URLShortner.dto.ShortnerURLDto;
import org.springframework.stereotype.Service;

@Service
public class URLShortnerService implements IURLShortner {

    private final URLShortnerRepository repository;
    private final URLShortnerMapper mapper;
    private final SnowflakeIDGeneratorService snowflakeIDGeneratorService;
    private final Base64ConverterService base64ConverterService;

    public URLShortnerService(
            URLShortnerRepository repository,
            URLShortnerMapper mapper,
            SnowflakeIDGeneratorService snowflakeIDGeneratorService,
            Base64ConverterService base64ConverterService
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.snowflakeIDGeneratorService = snowflakeIDGeneratorService;
        this.base64ConverterService = base64ConverterService;
    }

    @Override
    public URLShortnerModel shortenURL(ShortnerURLDto dto) {
        Long generatedID = snowflakeIDGeneratorService.generate();
        String shortURL = base64ConverterService.encode(generatedID);

        URLShortnerModel model = new URLShortnerModel();
        model.setId(generatedID);
        model.setShortURL(shortURL);
        model.setActualURL(dto.getUrl());

        URLShortnerEntity entity = mapper.toEntity(model);
        URLShortnerEntity created = repository.save(entity);

        return mapper.toModel(created);
    }

    @Override
    public URLShortnerModel getShortURL(String encodedID) {
        URLShortnerEntity urlShortnerEntity = repository.findByShortUrl(encodedID).orElse(null);
        URLShortnerModel urlShortnerModel = mapper.toModel(urlShortnerEntity);

        return urlShortnerModel;
    }

}
