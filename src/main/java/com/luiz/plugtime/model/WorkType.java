package com.luiz.plugtime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "work_type")
public class WorkType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String type;

    private BigDecimal estimatedCost;

    @ManyToMany(mappedBy = "workTypes")
    private Set<WorkOrder> workOrders;
}
