package com.avmathman.elasticurl.domain.SnowflakeIDGenerator;

import com.avmathman.elasticurl.common.enums.KeyEnum;

import java.util.Map;

public interface ISnowflakeIDGenerator {

    /**
     * Initializes data based on paramters of current method.
     *
     * @param datacenterId - The data center current application.
     * @param machineId - The machine ID current application.
     * @param epoch - The epoch current application.
     */
    public void init(Long datacenterId, Long machineId, Long epoch);

    /**
     * Returns generated {@link Long} ID.
     *
     * @return The generated {@link Long} ID.
     */
    public long generate();

    /**
     * Returns generated ID information from timestamp, datacenter, machine, sequence.
     *
     * @param id - The {@link Long} ID.
     * @return The parsed information for timestamp, datacenter, machine, sequence based on ID.
     */
    public Map<KeyEnum, Long> parse(long id);
}
