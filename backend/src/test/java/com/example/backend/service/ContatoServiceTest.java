package com.example.backend.service;

import com.example.backend.exception.EntidadeEmUsoException;
import com.example.backend.exception.RegistroInvalidoException;
import com.example.backend.model.Contato;
import com.example.backend.repository.ContatoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    RegistroInvalidoException thrown = assertThrows(
            RegistroInvalidoException.class,
            () -> contatoService.salvar(contato)
    );

    assertEquals("Email inválido",thrown.getMessage());
  }

  @Test
  void deveFalharAoTentarCadastrarUmTelefoneInvalido() {
    var contato = new Contato();
    contato.setEmail("aaaa@aaa.com");
    contato.setTelefone("45-aa4585-");

    RegistroInvalidoException thrown = assertThrows(
            RegistroInvalidoException.class,
            () -> contatoService.salvar(contato)
    );

    assertEquals("Telefone inválido",thrown.getMessage());
  }

  @Test
  void deveFalharAoTentarCadastrarUmTelefoneRepetido() {
    var contato = new Contato();
    contato.setEmail("a@a.com");
    contato.setTelefone("4545454545");

    Mockito.when(contatoRepository.existsByTelefone(contato.getTelefone())).thenReturn(true);
    EntidadeEmUsoException thrown = assertThrows(
            EntidadeEmUsoException.class,
            () -> contatoService.salvar(contato)
    );

    assertEquals("Telefone já cadastrado",thrown.getMessage());
  }

  @Test
  void deveFalharAoTentarCadastrarUmEmailRepetido() {
    var contato = new Contato();
    contato.setEmail("a@a.com");
    contato.setTelefone("4545454545");

    Mockito.when(contatoRepository.existsByTelefone(contato.getTelefone())).thenReturn(false);
    Mockito.when(contatoRepository.existsByEmail(contato.getEmail())).thenReturn(true);

    EntidadeEmUsoException thrown = assertThrows(
            EntidadeEmUsoException.class,
            () -> contatoService.salvar(contato)
    );

    assertEquals("Email já cadastrado",thrown.getMessage());
  }
}
