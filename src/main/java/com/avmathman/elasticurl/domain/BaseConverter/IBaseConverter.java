package com.avmathman.elasticurl.domain.BaseConverter;

public interface IBaseConverter {

    /**
     * Returns encoded Base64 ID.
     *
     * @param value - The {@link Long} ID.
     * @return The encoded Base64 ID.
     */
    public String encode(long value);

    /**
     * Returns decoded {@link Long} ID.
     *
     * @param value - The Base64 ID.
     * @return The decoded {@link Long} ID.
     */
    public long decode(String value);
}
