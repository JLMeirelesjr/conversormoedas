package br.com.projetoapi.conversor_moedas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Requisição Inválida", ex.getMessage());
    }

    // Captura erros retornados pela AwesomeAPI quando a moeda não existe (HTTP 404/400)
    @ExceptionHandler({HttpClientErrorException.class, RestClientException.class})
    public ResponseEntity<Map<String, Object>> handleRestClientException(Exception ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, "Moeda Inválida", "Moeda ou par de conversão não encontrado na API externa.");
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String error, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }
}