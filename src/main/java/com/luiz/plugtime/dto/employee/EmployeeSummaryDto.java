package com.luiz.plugtime.dto.employee;

import java.util.UUID;

public record EmployeeSummaryDto(UUID id, String name, String email) {
}
