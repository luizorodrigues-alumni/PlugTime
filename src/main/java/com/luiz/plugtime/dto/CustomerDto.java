package com.luiz.plugtime.dto;

import java.util.UUID;

public record CustomerDto(UUID id, String name, String phone, String email) {
}
