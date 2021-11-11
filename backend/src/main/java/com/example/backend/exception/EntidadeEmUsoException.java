package com.example.backend.exception;

public class EntidadeEmUsoException extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public EntidadeEmUsoException(String mensagem) {
    super(mensagem);
  }

  public EntidadeEmUsoException(String mensagem, Throwable causa) {
    super(mensagem, causa);
  }
}
