package com.jenschen.elastic.convert;

import org.springframework.data.elasticsearch.core.mapping.PropertyValueConverter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LocalDateTimeConverter implements PropertyValueConverter {
    @Override
    public Object write(Object value) {
        if(value instanceof LocalDateTime){
            //TODO
            return ((LocalDateTime) value).toEpochSecond(ZoneOffset.ofHours(8));
        }
        return null;
    }

    @Override
    public Object read(Object value) {
        if(value instanceof Long){
            //TODO
            long v = (Long) value;
            return LocalDateTime.ofEpochSecond(v /1000,0,ZoneOffset.ofHours(8));
        }
        return null;
    }
}
