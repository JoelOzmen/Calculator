package com.project.calculator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.calculator.controller.CalculatorController;
import com.project.calculator.model.CalculationRequest;
import com.project.calculator.model.OperationType;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(CalculatorController.class)
@ComponentScan(basePackages = "com.project.calculator.service")
public class CalculatorControllerTest {

    private static final Logger log = LoggerFactory.getLogger(CalculatorControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //*********************************MULTIPLICATION*********************************
    @Test
    public void multiply() throws Exception {
        CalculationRequest request = new CalculationRequest(OperationType.MULTIPLY, 2.0, 3.0);

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        double actualResult = Double.parseDouble(result);
        double expectedResult = 6.0;

        assertEquals(expectedResult, actualResult);
        log.info("Assertion for test expected: = {} and was: = {}", expectedResult, actualResult);
    }

    @Test
    public void multiplyUsingNegativeNumbers() throws Exception {
        CalculationRequest request = new CalculationRequest(OperationType.MULTIPLY, -2.5, 4.0);

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        double actualResult = Double.parseDouble(result);
        double expectedResult = -10.0;

        assertEquals(expectedResult, actualResult);
        log.info("Assertion for test expected: = {} and was: = {}", expectedResult, actualResult);
    }

    @Test
    public void multiplyUsingLargeNumbers() throws Exception {
        CalculationRequest request = new CalculationRequest(OperationType.MULTIPLY, 1.0e6, 2.0e6);

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        double actualResult = Double.parseDouble(result);
        double expectedResult = 2.0e12;

        assertEquals(expectedResult, actualResult);
        log.info("Assertion for test expected: = {} and was: = {}", expectedResult, actualResult);
    }

    //*********************************DIVISION*********************************
    @Test
    public void divide() throws Exception {
        CalculationRequest request = new CalculationRequest(OperationType.DIVIDE, 6.0, 2.0);

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        double actualResult = Double.parseDouble(result);
        double expectedResult = 3.0;

        assertEquals(expectedResult, actualResult);
        log.info("Assertion for test expected: = {} and was: = {}", expectedResult, actualResult);
    }

    @Test
    public void divideToDecimal() throws Exception {
        CalculationRequest request = new CalculationRequest(OperationType.DIVIDE, 7.0, 2.0);

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        double actualResult = Double.parseDouble(result);
        double expectedResult = 3.5;

        assertEquals(expectedResult, actualResult);
        log.info("Assertion for test expected: = {} and was: = {}", expectedResult, actualResult);
    }

    @Test
    public void divideNegativeByPositive() throws Exception {
        CalculationRequest request = new CalculationRequest(OperationType.DIVIDE, -8.0, 2.0);

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        double actualResult = Double.parseDouble(result);
        double expectedResult = -4.0;

        assertEquals(expectedResult, actualResult);
        log.info("Assertion for test expected: = {} and was: = {}", expectedResult, actualResult);
    }

    @Test
    public void divideZeroByValidNumber() throws Exception {
        CalculationRequest request = new CalculationRequest(OperationType.DIVIDE, 0.0, 3.0);

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        double actualResult = Double.parseDouble(result);
        double expectedResult = 0.0;

        assertEquals(expectedResult, actualResult);
        log.info("Assertion for test expected: = {} and was: = {}", expectedResult, actualResult);
    }

    @Test
    public void divideByZero() throws Exception {
        CalculationRequest request = new CalculationRequest(OperationType.DIVIDE, 6.0, 0.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    //*********************************UNKNOWN*********************************
    @Test
    public void calculateUnknownOperation() throws Exception {
        CalculationRequest request = new CalculationRequest(OperationType.UNKNOWN, 6.0, 2.0);

        mockMvc.perform(MockMvcRequestBuilders.post("/calculator/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
