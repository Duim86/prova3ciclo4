import { useState, useEffect } from 'react'
import { useParams } from "react-router";
import Header from "../components/Header"
import api from '../services/api';

function DetalhesContato() {
  const params = useParams();

  const [contato, setContato] = useState({
    nome: "",
    telefone: "",
    email: ""
  })
  

  useEffect(() => {
    api.get(`/contato/${params.id}`).then(res => {
      setContato(res.data)
    });
  }, [params.id]) 

  return (
    <>
    <Header />
      <main>
        <div className="container mt-4">
            <div className="row">
                <div className="col-12">
                    <p><b>Id:</b> <span>{params.id}</span></p>
                    <p><b>Nome:</b> <span>{contato.nome}</span></p>
                    <p><b>Telefone</b> <span>{contato.telefone}</span></p>
                    <p><b>Email</b> <span>{contato.email}</span></p>
                </div>
            </div>
            <div className="row">
                <div className="col-12">
                    <a className="btn btn-primary mt-5" href={`/contato/editar/${params.id}`}>Editar</a>
                </div>
            </div>
        </div>
      </main>
    </>
  )
}

export default DetalhesContato
