package com.example.backend.service;

import com.example.backend.model.Contato;
import com.example.backend.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {
  @Autowired
  private ContatoRepository contatoRepository;

  public List<Contato> listar() {
    return contatoRepository.findAll();
  }

  public Contato buscar(Long contatoId) {
    return contatoRepository.findById(contatoId)
            .orElseThrow(() -> new IllegalStateException("Contato não encontrada"));
  }

  public Contato salvar(Contato contato) {
    return contatoRepository.save(contato);
  }

  public Contato atualizar(Long contatoId, Contato contato) {
    Contato contatoAtual = buscar(contatoId);
    contatoAtual.setNome(contato.getNome());
    return salvar(contatoAtual);
  }

  public void remover(Long contatoId) {
    Contato contato = buscar(contatoId);
    contatoRepository.delete(contato);
  }
}
