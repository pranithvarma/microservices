package com.eazybytes.cards.audit;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class MyMaskingSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null && value.length() > 4) {
            gen.writeString("****" + value.substring(value.length() - 4)); // Mask all but last 4
        } else if (value != null) {
            gen.writeString("****"); // Mask short values completely
        } else {
            gen.writeNull();
        }
    }
}
