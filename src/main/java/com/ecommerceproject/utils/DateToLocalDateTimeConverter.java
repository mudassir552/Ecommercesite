package com.ecommerceproject.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

// Reading converter to convert Date to LocalDateTime
@ReadingConverter
public class DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {
    @Override
    public LocalDateTime convert(Date source) {
        return source.toInstant().atZone(ZoneOffset.UTC).toLocalDateTime();
    }
}
