package com.jenschen.elastic.convert;

import org.springframework.data.elasticsearch.core.mapping.PropertyValueConverter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LocalDateTimeConverter implements PropertyValueConverter {
    @Override
    public Object write(Object value) {
        if(value instanceof LocalDateTime){
            return ((LocalDateTime) value).toEpochSecond(ZoneOffset.ofHours(8));
        }
        return null;
    }

    @Override
    public Object read(Object value) {
        if(value instanceof Integer){
            long v = (Integer) value;
            return LocalDateTime.ofEpochSecond(v ,0,ZoneOffset.ofHours(8));
        }
        return null;
    }
}
