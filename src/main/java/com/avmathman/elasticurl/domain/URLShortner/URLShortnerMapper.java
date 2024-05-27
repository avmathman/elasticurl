package com.avmathman.elasticurl.domain.URLShortner;

import com.avmathman.elasticurl.data.URLShortner.URLShortnerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface URLShortnerMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "shortURL", source = "shortURL")
    @Mapping(target = "actualURL", source = "actualURL")
    @Mapping(target = "createdAt", source = "createdAt")
    public abstract URLShortnerModel toModel(URLShortnerEntity entity);

    @Mapping(target = "id", source = "id", qualifiedByName = "id")
    @Mapping(target = "shortURL", source = "shortURL", qualifiedByName = "shortURL")
    @Mapping(target = "actualURL", source = "actualURL", qualifiedByName = "actualURL")
    @Mapping(target = "createdAt", ignore = true)
    public abstract URLShortnerEntity toEntity(URLShortnerModel model);
}
