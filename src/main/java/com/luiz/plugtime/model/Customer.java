package com.luiz.plugtime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String cpf;

    private String cep;
    private String neighborhood;
    private String street;
    private Integer number;
    private String complement;
    private String phone;
    private String email;
}
