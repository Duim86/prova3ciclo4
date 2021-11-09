import {useState, useEffect } from "react"
import Header from "../components/Header"
import api from "../services/api"

function Landing() {
  const [contatos, setContatos] = useState([])

  useEffect(() => {
    api.get('/contato').then(res => setContatos(res.data));
  }, [])

  function removerContato(contatoId) {
    api.delete(`/contato/${contatoId}`).then(() => {
      alert('Registro removido com sucesso!');
      window.location.reload()
      
    }).catch(() => {
        alert('Erro ao remover!');
    })
  }

  return (
    <>
      <Header />
      <div className="container mt-4">
        <div className="row">
          <div className="col-12">
            <table className="table table-hover">
              <thead>     
                <tr>
                  <th scope="col">ID</th>
                  <th scope="col">Nome</th>
                  <th scope="col">email</th>
                  <th scope="col"></th>
                  <th scope="col"></th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>
              {contatos.map(contato => {
                return (                
                  <tr key={contato.id}>
                    <th id={`contato-${contato.id}`} className="col-1" scope="row">{contato.id}</th>
                    <td id={`nome-${contato.id}`} className="col-4">{contato.nome}</td>
                    <td id={`email-${contato.id}`} className="col-2">{contato.email}</td>
                    <td id={`detalhes-${contato.id}`} className="col-1"><a className="btn btn-success" href={`/contato/detalhes/${contato.id}`}>Detalhes</a></td>
                    <td id={`editar-${contato.id}`} className="col-1"><a className="btn btn-secondary" href={`/contato/editar/${contato.id}`}>Editar</a></td>
                    <td id={`remover-${contato.id}`} className="col-1"><button className="btn btn-danger" onClick={(e) => removerContato(contato.id)}>Deletar</button></td>
                  </tr>
                )                  
                })}
              </tbody>
            </table>
          </div>
        </div>
        <div className="row">
          <a id="btn-nova-contato" className="btn btn-primary btn-lg d-block" href="/contato/novo">Novo Contato</a>
        </div>
      </div>
    </>    
    )
  }
  
  export default Landing
