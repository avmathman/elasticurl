package com.avmathman.elasticurl.domain.BaseConverter;

import org.springframework.stereotype.Service;

@Service
public class Base64ConverterService implements IBaseConverter {

    private final int MAX_LENGTH = 7;
    private final long BASE_VALUE = 62;
    private final String BASE_DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    @Override
    public String encode(long value) {
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while (i < MAX_LENGTH && value != 0) {
            int remainder = (int) (value % BASE_VALUE);
            char current = BASE_DIGITS.charAt(remainder);
            sb.append(current);

            value = value / BASE_VALUE;
        }

        return sb.reverse().toString();
    }

    @Override
    public long decode(String value) {
        char[] chars = value.toCharArray();
        long result = 0;

        int counter = chars.length - 1;
        while(counter >= 0) {
            long current_index = BASE_DIGITS.indexOf(chars[counter]);
            long current_value = current_index * (long)(Math.pow(62, counter));

            result += current_value;
            counter--;
        }

        return result;
    }
}
