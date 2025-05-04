package com.luiz.plugtime.service;

import com.luiz.plugtime.dto.WorkTypeDto;
import com.luiz.plugtime.model.WorkType;
import com.luiz.plugtime.repository.WorkTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
public class WorkTypeService {
    private WorkTypeRepository repository;

    public WorkTypeService(WorkTypeRepository repository) {
        this.repository = repository;
    }

    // Creates a new WorkType
    public WorkTypeDto createWorkType(WorkTypeDto dto){
        WorkType workType = new WorkType();
        BeanUtils.copyProperties(dto, workType);

        WorkType savedWorkType = repository.save(workType);
        return new WorkTypeDto(
                savedWorkType.getType(),
                savedWorkType.getEstimatedCost()
        );
    }
}
