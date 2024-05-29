package com.avmathman.elasticurl.domain.SnowflakeIDGenerator;

import com.avmathman.elasticurl.common.enums.KeyEnum;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SnowflakeIDGeneratorServiceTest {

    @Test
    public void generate_setsDataCenterIdOneAndMachineIdOne_validatesParsedId() {

        // Assign
        SnowflakeIDGeneratorService generator = new SnowflakeIDGeneratorService(1,2);
        long beforeTimestamp = Instant.now().toEpochMilli();

        // Act
        long id = generator.generate();
        Map<KeyEnum, Long> attrs = generator.parse(id);

        // Assert
        assertTrue(attrs.get(KeyEnum.TIMESTAMP) >= beforeTimestamp);
        assertEquals(1, attrs.get(KeyEnum.DATACENTER));
        assertEquals(2, attrs.get(KeyEnum.MACHINE));
        assertEquals(0, attrs.get(KeyEnum.SEQUENCE));
    }

    @Test
    public void generate_generated5000IDs_validateIdsAreNotSame() {

        // Assign
        SnowflakeIDGeneratorService generator = new SnowflakeIDGeneratorService(3);
        int iterations = 5000;

        // Act
        long[] ids = new long[iterations];
        for(int i = 0; i < iterations; i++) {
            ids[i] = generator.generate();
        }
        Arrays.sort(ids);

        // Assert
        for (int i = 0, j=1; i < ids.length - 2; i++, j++) {
            assertNotEquals(ids[i], ids[j]);
        }
    }

    @Test
    public void generate_generatedIDsInDifferentThreads_validateIdsAreNotSame() throws InterruptedException, ExecutionException {

        // Assign
        int numThreads = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch latch = new CountDownLatch(numThreads);

        SnowflakeIDGeneratorService generator = new SnowflakeIDGeneratorService(4);
        int iterations = 10000;

        // Act
        Future<Long>[] futures = new Future[iterations];
        for(int i = 0; i < iterations; i++) {
            futures[i] =  executorService.submit(() -> {
                long id = generator.generate();
                latch.countDown();;
                return id;
            });
        }
        latch.await();

        // Assert
        for(int i = 0; i < futures.length; i++) {
            for(int j = i+1; j < futures.length; j++) {
                assertNotEquals(futures[i].get(), futures[j].get());
            }
        }
    }
}
