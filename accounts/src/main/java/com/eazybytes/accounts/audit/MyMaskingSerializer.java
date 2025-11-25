package com.eazybytes.accounts.audit;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class MyMaskingSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        if (value == null) {
            gen.writeNull();
            return;
        }

        String strValue = value.toString();
        String masked = mask(strValue);

        gen.writeString(masked);
    }

    private String mask(String value) {
        if (value.length() <= 4) {
            return value; // Keep as is if length is 4 or less
        }

        int maskLength = value.length() - 4;
        String mask = "*".repeat(maskLength);

        return mask + value.substring(value.length() - 4);
    }
}
