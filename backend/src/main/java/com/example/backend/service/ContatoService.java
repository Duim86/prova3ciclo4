package com.example.backend.service;

import com.example.backend.exception.EmailConflictException;
import com.example.backend.exception.EmailInvalidoException;
import com.example.backend.exception.TelefoneConflictException;
import com.example.backend.exception.TelefoneInvalidoException;
import com.example.backend.model.Contato;
import com.example.backend.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class ContatoService {
  private static final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
  private static final Pattern TELEFONE_REGEX = Pattern.compile("[0-9-]*");

  @Autowired
  private ContatoRepository contatoRepository;

  public List<Contato> listar() {
    return contatoRepository.findAll();
  }

  public Contato buscar(Long contatoId) {
    return contatoRepository.findById(contatoId)
            .orElseThrow(() -> new IllegalStateException("Contato não encontrado"));
  }

  @Transactional
  public Contato salvar(Contato contato) {
    validaEmail(contato.getEmail());
    validaTelefone(contato.getTelefone());
    existsByTelefone(contato.getTelefone());
    existsByEmail(contato.getEmail());


    return contatoRepository.save(contato);
  }

  @Transactional
  public Contato atualizar(Long contatoId, Contato contato) {
    Contato contatoAtual = buscar(contatoId);
    contatoAtual.setNome(contato.getNome());
    contatoAtual.setTelefone(contato.getTelefone());
    contatoAtual.setEmail(contato.getEmail());
    return salvar(contatoAtual);
  }

  public void remover(Long contatoId) {
    Contato contato = buscar(contatoId);
    contatoRepository.delete(contato);
  }

  public void existsByEmail(String email) {
    if(contatoRepository.existsByEmail(email)) {
      throw new EmailConflictException("Email já cadastrado");
    }
  }

  public void existsByTelefone(String email) {
    if(contatoRepository.existsByTelefone(email)) {
      throw new TelefoneConflictException("Email já cadastrado");
    }
  }

  public void validaEmail(String email) {
    if(!EMAIL_REGEX.matcher(email).matches()) {
      throw new EmailInvalidoException("Email inválido");
    }
  }

  public void validaTelefone(String telefone) {
    if(!telefone.matches(String.valueOf(TELEFONE_REGEX))){
      throw new TelefoneInvalidoException("Telefone inválido");
    }
  }
}
