package io.mirko.entity;

import javax.json.bind.annotation.JsonbTypeDeserializer;
import javax.json.bind.annotation.JsonbTypeSerializer;


@JsonbTypeSerializer(CountrySerializer.class)
@JsonbTypeDeserializer(CountryDeserializer.class)
public enum Country {
    IT,
    US,
    UK,
    FR,
    DE;

}
