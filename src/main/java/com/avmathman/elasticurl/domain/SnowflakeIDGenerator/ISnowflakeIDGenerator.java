package com.avmathman.elasticurl.domain.SnowflakeIDGenerator;

import com.avmathman.elasticurl.common.enums.KeyEnum;

import java.util.Map;

public interface ISnowflakeIDGenerator {

    public void init(Long datacenterId, Long machineId, Long epoch);

    public long generate();

    public Map<KeyEnum, Long> parse(long id);
}
