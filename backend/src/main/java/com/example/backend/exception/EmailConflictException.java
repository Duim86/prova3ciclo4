package com.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailConflictException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public EmailConflictException(String mensagem) {
    super(mensagem);
  }

  public EmailConflictException(String mensagem, Throwable causa) {
    super(mensagem, causa);
  }
}
