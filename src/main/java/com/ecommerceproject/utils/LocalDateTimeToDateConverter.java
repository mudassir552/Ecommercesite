package com.ecommerceproject.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

// Writing converter to convert LocalDateTime to Date
@WritingConverter
public class LocalDateTimeToDateConverter implements Converter<LocalDateTime, Date> {
    @Override
    public Date convert(LocalDateTime source) {
        return Date.from(source.atOffset(ZoneOffset.UTC).toInstant());
    }
}

