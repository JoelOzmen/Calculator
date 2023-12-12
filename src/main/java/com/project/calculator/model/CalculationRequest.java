package com.project.calculator.model;

public class CalculationRequest {
    private OperationType operationType;
    private double firstOperand;
    private double secondOperand;

    public CalculationRequest(OperationType operationType, double firstOperand, double secondOperand) {
        this.operationType = operationType;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    public OperationType getOperationType() {
        return operationType;
    }
    public double getFirstOperand() {
        return firstOperand;
    }
    public double getSecondOperand() {
        return secondOperand;
    }
}
