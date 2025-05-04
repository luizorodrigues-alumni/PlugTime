package com.luiz.plugtime.controller;

import com.luiz.plugtime.dto.WorkTypeDto;
import com.luiz.plugtime.service.WorkTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/worktype")
public class WorkTypeController {
    private WorkTypeService service;

    public WorkTypeController(WorkTypeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WorkTypeDto> createWorkType(@RequestBody WorkTypeDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createWorkType(dto));
    }
}
