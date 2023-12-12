package com.project.calculator.service;

import com.project.calculator.model.OperationType;
import org.springframework.stereotype.Service;

@Service
public class MultiplyOperationService implements OperationService {
    @Override
    public double calculate(double firstOperand, double secondOperand) {
        return firstOperand * secondOperand;
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.MULTIPLY;
    }
}