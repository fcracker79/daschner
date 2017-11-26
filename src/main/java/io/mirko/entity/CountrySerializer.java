package io.mirko.entity;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;

public class CountrySerializer implements JsonbSerializer<Country> {
    @Override
    public void serialize(Country obj, JsonGenerator generator, SerializationContext ctx) {
        if (obj != null) {
            generator.write(obj.name());
        } else {
            generator.writeNull();
        }
    }
}
