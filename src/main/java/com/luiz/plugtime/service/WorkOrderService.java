package com.luiz.plugtime.service;

import com.luiz.plugtime.dto.WorkOrderDto;
import com.luiz.plugtime.model.Customer;
import com.luiz.plugtime.model.Employee;
import com.luiz.plugtime.model.ServiceType;
import com.luiz.plugtime.model.WorkOrder;
import com.luiz.plugtime.repository.CustomerRepository;
import com.luiz.plugtime.repository.EmployeeRepository;
import com.luiz.plugtime.repository.ServiceTypeRepository;
import com.luiz.plugtime.repository.WorkOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkOrderService {
    private WorkOrderRepository workOrderRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private ServiceTypeRepository serviceTypeRepository;

    public WorkOrderService(
            WorkOrderRepository workOrderRepository,
            CustomerRepository customerRepository,
            EmployeeRepository employeeRepository,
            ServiceTypeRepository serviceTypeRepository
    ) {
        this.workOrderRepository = workOrderRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.serviceTypeRepository = serviceTypeRepository;
    }
    @Transactional
    public WorkOrderDto createWorkOrder(WorkOrderDto dto){

        // Checks if the customer exists
        Customer customer = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));

        // Checks if the employee exists
        Employee employee = employeeRepository.findById(dto.employeeId())
                .orElseThrow(() -> new RuntimeException("Employee Not Found"));

        // Checks if the Service/Work types exists
        Set<ServiceType> serviceTypes = new HashSet<>();
        for(Long id : dto.serviceTypeIds()){
            ServiceType serviceType = serviceTypeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Service Type not found for id: " + id));
            serviceTypes.add(serviceType);
        }

        WorkOrder workOrder = new WorkOrder();
        workOrder.setCustomer(customer);
        workOrder.setEmployee(employee);
        workOrder.setAmount(dto.amount());
        workOrder.setScheduledTime(dto.scheduledTime());
        workOrder.setStatus(dto.status());
        workOrder.setServiceTypes(serviceTypes);

        WorkOrder savedWorOrder = workOrderRepository.save(workOrder);
        return new WorkOrderDto(
                savedWorOrder.getCustomer().getId(),
                savedWorOrder.getEmployee().getId(),
                savedWorOrder.getServiceTypes().stream().map(ServiceType::getId).collect(Collectors.toSet()),
                savedWorOrder.getAmount(),
                savedWorOrder.getScheduledTime(),
                savedWorOrder.getStatus()
        );

    }
}
