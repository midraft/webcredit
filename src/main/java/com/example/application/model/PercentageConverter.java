package com.example.application.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;

@Converter
public class PercentageConverter implements AttributeConverter<Double, BigDecimal> {


    @Override
    public BigDecimal convertToDatabaseColumn(Double bigDecimal) {
        if (bigDecimal == null) {
            return null;
        }
        return new BigDecimal(bigDecimal);
    }

    @Override
    public Double convertToEntityAttribute(BigDecimal bigDecimal) {
        return bigDecimal.doubleValue();
    }
}