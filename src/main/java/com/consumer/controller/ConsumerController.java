package com.consumer.controller;

import com.consumer.entity.Employee;
import com.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;
    @GetMapping("/")
    public ResponseEntity<List<String>> fetchData(){
        return new ResponseEntity<>(consumerService.fetchPayloads(), HttpStatus.OK);
    }

    @GetMapping("/fetch")
    public ResponseEntity<List<Employee>> fetchEmployeesData(){
        return new ResponseEntity<>(consumerService.fetchEmployeesData(), HttpStatus.OK);
    }
}
