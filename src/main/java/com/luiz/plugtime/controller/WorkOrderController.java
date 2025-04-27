package com.luiz.plugtime.controller;

import com.luiz.plugtime.dto.WorkOrderDto;
import com.luiz.plugtime.service.WorkOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class WorkOrderController {
    private WorkOrderService service;

    public WorkOrderController(WorkOrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WorkOrderDto> createWorkOrder(@RequestBody WorkOrderDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createWorkOrder(dto));
    }
}
