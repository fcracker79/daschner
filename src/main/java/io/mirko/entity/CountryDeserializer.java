package io.mirko.entity;

import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import javax.validation.ValidationException;
import java.lang.reflect.Type;
import java.util.logging.Logger;

public class CountryDeserializer implements JsonbDeserializer<Country> {

    @Override
    public Country deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {
        final String strValue = parser.getString();
        try {
            return strValue == null ? null : Country.valueOf(strValue);
        } catch(java.lang.IllegalArgumentException e) {
            Logger.getLogger("CountryDeserializer.deserialize").warning("Could not deserialize " + strValue);
            throw new ValidationException();
        }
    }
}
