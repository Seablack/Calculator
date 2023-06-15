package org.sanitas.calculator.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface CalculatorController {
    @GetMapping("/add")
    Double add(@RequestParam double num1, @RequestParam double num2);

    @GetMapping("subtract")
    Double subtract(@RequestParam double num1, @RequestParam double num2);
}
