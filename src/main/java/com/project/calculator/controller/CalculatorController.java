package com.project.calculator.controller;

import com.project.calculator.model.CalculationRequest;
import com.project.calculator.model.OperationType;
import com.project.calculator.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private static final Logger log = LoggerFactory.getLogger(CalculatorController.class);

    @Autowired
    private Map<OperationType, OperationService> operationServices;

    @PostMapping("/calculate")
    public ResponseEntity<String> calculate(@RequestBody CalculationRequest request) {
        OperationService operationService = operationServices.get(request.getOperationType());

        if (operationService != null) {
            try {
                double result = operationService.calculate(request.getFirstOperand(), request.getSecondOperand());
                return ResponseEntity.ok(Double.toString(result));
            } catch (ArithmeticException e) {
                // Handles division by zero
                log.error("ArithmeticException: {}", e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Division by zero.");
            }
        } else {
            // Handles unknown operation type
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: Unsupported operation type - " + request.getOperationType());
        }
    }

}