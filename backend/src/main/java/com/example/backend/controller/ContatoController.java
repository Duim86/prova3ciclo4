package com.example.backend.controller;

import com.example.backend.model.Contato;
import com.example.backend.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contato")
@CrossOrigin(origins = "http://localhost:3000")
public class ContatoController {
  @Autowired
  private ContatoService contatoService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Contato> listar() {
    return contatoService.listar();
  }

  @GetMapping("/{contatoId}")
  @ResponseStatus(HttpStatus.OK)
  public Contato buscar(@PathVariable Long contatoId) {
    return contatoService.buscar(contatoId);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Contato salvar(@RequestBody Contato contato) {
    return contatoService.salvar(contato);
  }


  @PutMapping("/{contatoId}")
  public Contato atualizar(@PathVariable Long contatoId,
                          @RequestBody Contato contato){
    return contatoService.atualizar(contatoId, contato);
  }

  @DeleteMapping("/{contatoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable Long contatoId) {
    contatoService.remover(contatoId);
  }
}
