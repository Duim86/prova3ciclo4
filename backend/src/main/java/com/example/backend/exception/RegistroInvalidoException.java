package com.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegistroInvalidoException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public RegistroInvalidoException(String mensagem) {
    super(mensagem);
  }

  public RegistroInvalidoException(String mensagem, Throwable causa) {
    super(mensagem, causa);
  }
}
