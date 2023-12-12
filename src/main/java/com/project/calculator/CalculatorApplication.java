package com.project.calculator;

import com.project.calculator.model.OperationType;
import com.project.calculator.service.OperationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class CalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
	}

	@Bean
	public Map<OperationType, OperationService> operationServices(List<OperationService> services) {
		return services.stream().collect(Collectors.toMap(OperationService::getOperationType, Function.identity()));
	}

}
