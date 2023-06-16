package org.sanitas.calculator.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface CalculatorController {
    @GetMapping("/add")
    ResponseEntity<?> add(@RequestParam String num1, @RequestParam String num2);

    @GetMapping("subtract")
    ResponseEntity<?> subtract(@RequestParam String num1, @RequestParam String num2);
}
