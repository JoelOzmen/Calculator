package com.project.calculator.service;

import com.project.calculator.model.OperationType;

public interface OperationService {
    double calculate(double firstOperand, double secondOperand);

    OperationType getOperationType();
}
