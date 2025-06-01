package com.luiz.plugtime.dto.customer;

import java.util.UUID;

public record CustomerDto(UUID id, String name, String phone, String email) {
}
