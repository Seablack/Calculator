package org.sanitas.calculator.application;

import org.sanitas.calculator.domain.CalculatorController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorControllerImpl implements CalculatorController {

    @Override
    public Double add(double num1, double num2) {
        return null;
    }

    @Override
    public Double subtract(double num1, double num2) {
        return null;
    }
}
