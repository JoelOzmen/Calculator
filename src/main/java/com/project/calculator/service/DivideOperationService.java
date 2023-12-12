package com.project.calculator.service;

import com.project.calculator.model.OperationType;
import org.springframework.stereotype.Service;

@Service
public class DivideOperationService implements OperationService {
    @Override
    public double calculate(double firstOperand, double secondOperand) {
        if (secondOperand == 0.0) {
            throw new ArithmeticException("Division by zero");
        }
        return firstOperand / secondOperand;
    }

    @Override
    public OperationType getOperationType() {
        return OperationType.DIVIDE;
    }
}