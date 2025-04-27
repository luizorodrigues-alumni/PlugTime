package com.luiz.plugtime.dto;

import com.luiz.plugtime.enums.ServiceStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record WorkOrderDto(
        UUID customerId,
        UUID employeeId,
        Set<Long> serviceTypeIds,
        BigDecimal amount,
        LocalDateTime scheduledTime,
        ServiceStatus status
) {}
