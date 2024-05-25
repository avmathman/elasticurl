package com.avmathman.elasticurl.domain.BaseConverter;

public interface IBaseConverter {

    public String encode(long value);

    public long decode(String value);
}
