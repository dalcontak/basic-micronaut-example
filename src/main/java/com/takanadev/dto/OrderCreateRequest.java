package com.takanadev.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.math.BigDecimal;

@Introspected
@Serdeable
public record OrderCreateRequest(String customerName, BigDecimal cost) {
}