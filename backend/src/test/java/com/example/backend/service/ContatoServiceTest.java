package com.example.backend.service;

import com.example.backend.exception.EmailConflictException;
import com.example.backend.exception.EmailInvalidoException;
import com.example.backend.exception.TelefoneConflictException;
import com.example.backend.exception.TelefoneInvalidoException;
import com.example.backend.model.Contato;
import com.example.backend.repository.ContatoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ContatoServiceTest {

  @InjectMocks
  private ContatoService contatoService;

  @Mock
  private ContatoRepository contatoRepository;

  @Test
  void deveFalharAoTentarCadastrarUmEmailInvalido() {
    var contato = new Contato();
    contato.setEmail("aaaa.aaa");

    assertThrows(EmailInvalidoException.class, () -> contatoService.salvar(contato));
  }

  @Test
  void deveFalharAoTentarCadastrarUmTelefoneInvalido() {
    var contato = new Contato();
    contato.setTelefone("45-aa4585-");

    assertThrows(TelefoneInvalidoException.class, () -> contatoService.salvar(contato));
  }

  @Test
  void deveFalharAoTentarCadastrarUmTelefoneRepetido() {
    var contato = new Contato();
    contato.setEmail("a@a.com");
    contato.setTelefone("4545454545");

    Mockito.when(contatoRepository.existsByTelefone(contato.getTelefone())).thenReturn(true);
    assertThrows(TelefoneConflictException.class, () -> contatoService.salvar(contato));
  }

  @Test
  void deveFalharAoTentarCadastrarUmEmailRepetido() {
    var contato = new Contato();
    contato.setEmail("a@a.com");
    contato.setTelefone("4545454545");

    Mockito.when(contatoRepository.existsByTelefone(contato.getTelefone())).thenReturn(false);
    Mockito.when(contatoRepository.existsByEmail(contato.getEmail())).thenReturn(true);
    assertThrows(EmailConflictException.class, () -> contatoService.salvar(contato));
  }
}
