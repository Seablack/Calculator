package org.sanitas.calculator.infrastructure;

import org.sanitas.calculator.application.CalculatorController;
import org.sanitas.calculator.domain.CalculatorService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorControllerImpl implements CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorControllerImpl(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @Override
    public Double add(double num1, double num2) {
        return calculatorService.add(num1, num2);
    }

    @Override
    public Double subtract(double num1, double num2) {
        return calculatorService.subtract(num1, num2);
    }
}
