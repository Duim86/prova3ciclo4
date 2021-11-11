package com.example.backend.exception;

import com.example.backend.model.BodyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(RegistroInvalidoException.class)
  public ResponseEntity<Object> handleRegistroInvalidoException(RegistroInvalidoException ex, WebRequest request) {

    HttpStatus status = HttpStatus.BAD_REQUEST;

    BodyException bodyException = new BodyException(ex.getMessage());

    return handleExceptionInternal(ex, bodyException, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(EntidadeEmUsoException.class)
  public ResponseEntity<Object> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {

    HttpStatus status = HttpStatus.CONFLICT;

    BodyException bodyException = new BodyException(ex.getMessage());

    return handleExceptionInternal(ex, bodyException, new HttpHeaders(), status, request);
  }
}
