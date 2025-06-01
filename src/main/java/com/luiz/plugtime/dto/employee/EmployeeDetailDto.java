package com.luiz.plugtime.dto.employee;

import java.util.UUID;

public record EmployeeDetailDto(UUID id, String name, String phone, String cpf, String email) {
}
