package com.consumer.service;

import com.consumer.entity.Employee;
import com.consumer.repository.EmployeeRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumerService {

    List<String> messages=new ArrayList<>();

    @Autowired
    private EmployeeRepository repository;

//    @KafkaListener(topics ="EMPLOYEE_TOPIC",groupId = "emp-grp",containerFactory = "containerFactory")
//    public void reciveData(@Payload Employee employee, Acknowledgment ack){
//     repository.save(employee);
//    }

    @KafkaListener(topics ="EMPLOYEE_TOPIC",groupId = "emp-grp",containerFactory = "containerFactory")
    public void reciveData(String message){
        System.out.println(message);
        messages.add(message);
        Employee employee=new Gson().fromJson(message, Employee.class);
        System.out.println(employee.getEmpId());
        repository.save(employee);
    }



    public List<String> fetchPayloads(){
        return messages;
    }

    public List<Employee> fetchEmployeesData() {
        return  repository.findAll();
    }
}
