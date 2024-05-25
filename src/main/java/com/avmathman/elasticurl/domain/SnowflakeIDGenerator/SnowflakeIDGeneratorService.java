package com.avmathman.elasticurl.domain.SnowflakeIDGenerator;

import com.avmathman.elasticurl.common.constants.IDGeneratorConstants;
import com.avmathman.elasticurl.common.enums.KeyEnum;
import com.avmathman.elasticurl.common.utils.MachineUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class SnowflakeIDGeneratorService implements ISnowflakeIDGenerator {

    private long datacenterId;;
    private long machineId;;
    private long epoch;

    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;

    private MachineUtils machineUtils;

    public SnowflakeIDGeneratorService(long datacenterId, long machineId, long epoch) {
        if (machineId < 0 || machineId > IDGeneratorConstants.MAX_MACHINE_ID) {
            throw new IllegalArgumentException(String.format("MachineId must be between %d and %d", 0, IDGeneratorConstants.MAX_MACHINE_ID));
        }
        if (datacenterId < 0 || datacenterId > IDGeneratorConstants.MAX_DATACENTER_ID) {
            throw new IllegalArgumentException(String.format("DatacenterId must be between %d and %d", 0, IDGeneratorConstants.MAX_DATACENTER_ID));
        }

        this.datacenterId = datacenterId;
        this.machineId = machineId;
        this.epoch = epoch;
    }

    public SnowflakeIDGeneratorService(long datacenterId, long machineId) {
        this(datacenterId, machineId, IDGeneratorConstants.DEFAULT_EPOCH);
    }

    public SnowflakeIDGeneratorService(long machineId) {
        this(IDGeneratorConstants.DEFAULT_DATACENTER_ID, machineId, IDGeneratorConstants.DEFAULT_EPOCH);
    }

    public SnowflakeIDGeneratorService() {
        this.machineUtils = new MachineUtils();

        this.machineId = this.machineUtils.createMachineId();
        this.datacenterId = IDGeneratorConstants.DEFAULT_DATACENTER_ID;
        this.epoch = IDGeneratorConstants.DEFAULT_EPOCH;
    }

    @Override
    public void init(Long datacenterId, Long machineId, Long epoch) {
        if (this.machineId < 0 || this.machineId > IDGeneratorConstants.MAX_MACHINE_ID) {
            throw new IllegalArgumentException(String.format("MachineId must be between %d and %d", 0, IDGeneratorConstants.MAX_MACHINE_ID));
        }
        if (this.datacenterId < 0 || this.datacenterId > IDGeneratorConstants.MAX_DATACENTER_ID) {
            throw new IllegalArgumentException(String.format("DatacenterId must be between %d and %d", 0, IDGeneratorConstants.MAX_DATACENTER_ID));
        }

        this.datacenterId = datacenterId == null ? IDGeneratorConstants.DEFAULT_DATACENTER_ID : datacenterId;
        this.machineId = machineId == null ? this.machineUtils.createMachineId() : machineId;
        this.epoch = epoch == null ? IDGeneratorConstants.DEFAULT_EPOCH : epoch;
    }

    @Override
    public synchronized long generate() {
        long currentTimestamp = this.timestamp();

        if(currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Invalid System Clock!");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & IDGeneratorConstants.MAX_SEQUENCE;

            if(sequence == 0) {
                // Sequence Exhausted, wait till next millisecond.
                currentTimestamp = this.waitNextMillis(currentTimestamp);
            }
        } else {
            // reset sequence to start with zero for the next millisecond
            sequence = 0;
        }

        lastTimestamp = currentTimestamp;

        return currentTimestamp << (IDGeneratorConstants.DATACENTER_ID_BITS + IDGeneratorConstants.MACHINE_ID_BITS + IDGeneratorConstants.SEQUENCE_BITS)
                | this.datacenterId << (IDGeneratorConstants.MACHINE_ID_BITS + IDGeneratorConstants.SEQUENCE_BITS)
                | this.machineId << (IDGeneratorConstants.SEQUENCE_BITS)
                | this.sequence;
    }

    public Map<KeyEnum, Long> parse(long id) {
        long maskDatacenterId = ((1L << IDGeneratorConstants.DATACENTER_ID_BITS) - 1) << (IDGeneratorConstants.MACHINE_ID_BITS + IDGeneratorConstants.SEQUENCE_BITS);
        long maskMachineId = ((1L << IDGeneratorConstants.MACHINE_ID_BITS) - 1) << IDGeneratorConstants.SEQUENCE_BITS;
        long maskSequence = 1L << IDGeneratorConstants.SEQUENCE_BITS - 1;

        long timestamp = (id >> (IDGeneratorConstants.DATACENTER_ID_BITS + IDGeneratorConstants.MACHINE_ID_BITS + IDGeneratorConstants.SEQUENCE_BITS)) + this.epoch;
        long datacenterId = (id & maskDatacenterId) >> (IDGeneratorConstants.MACHINE_ID_BITS + IDGeneratorConstants.SEQUENCE_BITS);
        long machineId = (id & maskMachineId) >> IDGeneratorConstants.SEQUENCE_BITS;
        long sequence = id & maskSequence;

        Map<KeyEnum, Long> result = new HashMap<>();
        result.put(KeyEnum.TIMESTAMP, timestamp);
        result.put(KeyEnum.DATACENTER, datacenterId);
        result.put(KeyEnum.MACHINE, machineId);
        result.put(KeyEnum.SEQUENCE, sequence);

        return result;
    }

    private long timestamp() {
        return Instant.now().toEpochMilli() - this.epoch;
    }

    private long waitNextMillis(long currentTimestamp) {
        while(currentTimestamp == lastTimestamp) {
            currentTimestamp = timestamp();
        }

        return currentTimestamp;
    }
}
