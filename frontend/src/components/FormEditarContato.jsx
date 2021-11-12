import { useState, useEffect } from 'react'
import { useParams } from "react-router";
import { useNavigate } from 'react-router-dom';

import api from "../services/api"

function FormEditarContato() {

  const [nome, setNome] = useState("")
  const [telefone, setTelefone] = useState("")
  const [email, setEmail] = useState("")

  const params = useParams();
  const navigate = useNavigate();

  const contatoId = params.id;

  useEffect(() => {
    api.get(`/contato/${contatoId}`).then(res => {
      setNome(res.data.nome)
      setTelefone(res.data.telefone)
      setEmail(res.data.email)
    });
  }, [contatoId])


  function onSubmit(e) {
    e.preventDefault();

    api.put(`/contato/${contatoId}`, {
      contatoId,
      nome,
      telefone,
      email
    } ).then(() => {
        alert('Cadastro atualizado com sucesso!');
        navigate('/');
        
    }).catch((e) => {
        alert(e.response.data.message);
    })

  }

  return (
    <div className="container">
      <div className="row">
        <form onSubmit={onSubmit}>
          <div className="mt-3 mb-3">
            <label className="form-label">Nome do Contato</label>
            <input 
              className="form-control" 
              name="nome" 
              label="Nome completo" 
              value={nome} 
              onChange={(e)=>{setNome(e.target.value)}}
              type="text"
              required
            />
            </div>
              <div className="mb-3">
                <label className="form-label">Telefone</label>
                <input 
                  className="form-control" 
                  name="telefone" 
                  label="Telefone" 
                  value={telefone} 
                  onChange={(e)=>{setTelefone(e.target.value)}}
                  type="text"
                  required
                />
              </div>
              <div className="mb-3">
                <label className="form-label">E-mail</label>
                <input 
                  className="form-control" 
                  name="email" 
                  label="E-mail" 
                  value={email} 
                  onChange={(e)=>{setEmail(e.target.value)}}
                  type="e-mail"
                  required
                />
              </div>
              <button id="btn-cadastrar" className="btn btn-primary" type="submit">Cadastrar</button>
          </form>
      </div>
    </div>
  )
}

export default FormEditarContato;