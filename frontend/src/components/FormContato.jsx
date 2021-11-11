import { useState } from 'react'
import { useNavigate } from 'react-router-dom';

import api from "../services/api"

function FormContato() {

  const [nome, setNome] = useState("")
  const [telefone, setTelefone] = useState("")
  const [email, setEmail] = useState("")
  const navigate = useNavigate();

  function onSubmit(e) {
    e.preventDefault();

    api.post('/contato', {
      nome,
      telefone,
      email
    } ).then(() => {
        alert('Cadastro realizado com sucesso!');
        navigate('/');
        
    }).catch((error) => {
      console.log(error.response)
      alert('');
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

export default FormContato


