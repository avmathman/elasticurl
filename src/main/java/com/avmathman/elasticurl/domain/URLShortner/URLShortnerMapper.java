package com.avmathman.elasticurl.domain.URLShortner;

import com.avmathman.elasticurl.data.URLShortner.URLShortnerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface URLShortnerMapper {

    @Mapping(target = "id", source = ".", qualifiedByName = "id")
    @Mapping(target = "shortUrl", source = ".", qualifiedByName = "shortUrl")
    @Mapping(target = "actualUrl", source = ".", qualifiedByName = "actualUrl")
    @Mapping(target = "createdAt", source = ".", qualifiedByName = "createdAt")
    public abstract URLShortnerModel toModel(URLShortnerEntity entity);

    @Mapping(target = "id", source = ".", qualifiedByName = "id")
    @Mapping(target = "shortUrl", source = ".", qualifiedByName = "shortUrl")
    @Mapping(target = "actualUrl", source = ".", qualifiedByName = "actualUrl")
    @Mapping(target = "createdAt", ignore = true)
    public abstract URLShortnerEntity toEntity(URLShortnerModel model);
}
