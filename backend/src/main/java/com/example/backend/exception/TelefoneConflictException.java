package com.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TelefoneConflictException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public TelefoneConflictException(String mensagem) {
    super(mensagem);
  }

  public TelefoneConflictException(String mensagem, Throwable causa) {
    super(mensagem, causa);
  }
}
