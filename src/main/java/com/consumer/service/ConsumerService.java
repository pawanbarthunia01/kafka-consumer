package com.consumer.service;

import com.consumer.entity.Employee;
import com.consumer.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    private EmployeeRepository repository;

    @KafkaListener(topics ="EMPLOYEE_TOPIC",groupId = "emp-grp",containerFactory = "containerFactory")
    public void reciveData(@Payload Employee employee, Acknowledgment ack){
     repository.save(employee);
    }

    public List<Employee> fetchPayloads(){
        return repository.findAll();
    }
}
