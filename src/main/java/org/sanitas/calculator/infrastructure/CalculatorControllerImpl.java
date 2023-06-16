package org.sanitas.calculator.infrastructure;

import io.corp.calculator.TracerImpl;
import org.sanitas.calculator.application.CalculatorController;
import org.sanitas.calculator.domain.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorControllerImpl implements CalculatorController {

    private final CalculatorService calculatorService;
    private final TracerImpl tracer;

    public CalculatorControllerImpl(CalculatorService calculatorService, TracerImpl tracer) {
        this.calculatorService = calculatorService;
        this.tracer = tracer;
    }

    @Override
    public ResponseEntity<?> add(String num1, String num2) {
        try {
            double a = Double.parseDouble(num1);
            double b = Double.parseDouble(num2);
            double result = calculatorService.add(a, b);
            if (Double.isInfinite(result)) {
                throw new ArithmeticException();
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NumberFormatException | ArithmeticException e) {
            String msg = "Ambos argumentos deben ser números válidos.";
            if (e instanceof ArithmeticException) {
                msg = "Error en la operación de suma: El resultado es infinito.";
            }
            tracer.trace(msg);
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<?> subtract(String num1, String num2) {
        try {
            double a = Double.parseDouble(num1);
            double b = Double.parseDouble(num2);
            double result = calculatorService.subtract(a, b);
            if (Double.isInfinite(result)) {
                throw new ArithmeticException();
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NumberFormatException | ArithmeticException e) {
            String msg = "Ambos argumentos deben ser números válidos.";
            if (e instanceof ArithmeticException) {
                msg = "Error en la operación de resta: El resultado es infinito.";
            }
            tracer.trace(msg);
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }
}
